package com.nutrymaco.parser.job;

import com.nutrymaco.parser.model.tables.pojos.Vacancy;
import com.nutrymaco.parser.pojo.GJExperience;
import com.nutrymaco.parser.pojo.GJSalary;
import com.nutrymaco.parser.service.VacancyService;
import com.nutrymaco.parser.service.VacancyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.nutrymaco.parser.util.DateUtil.getNumberOfMonth;

@Slf4j
public class GJParser {

    private final static String NAME_SELECTOR = "#body > section > article.row.vacancy > section > header > h1";

    private final static String CITY_SELECTOR = "#body > section > article.row.vacancy > section > header > div.location";

    private final static String COMPANY_NAME_SELECTOR = "#body > section > article.row.vacancy > section > header > h5 > a";

    private final static String DESCRIPTION_SELECTOR = "#body > section > article.row.vacancy > section > div.description";

    private final static String DATE_SELECTOR = "#body > section > article.row.vacancy > section > header > div.time";

    private final static String EXPERIENCE_SELECTOR = "#body > section > article.row.vacancy > section > header > div.jobinfo > span.salary";

    private final static String SALARY_SELECTOR = "#body > section > article.row.vacancy > section > header > div.jobinfo > span.salary";

    private final static int MAX_COUNT_OF_REPEATED_VACANCIES = 5;

    private int currentCountOfRepeatedVacancies = 0;

    private final String baseUrl = "https://geekjob.ru";

    private final String vacanciesUrl = "https://geekjob.ru/vacancies";

    private int currentPage = 1;

    private final long periodInMillis;

    private boolean timeExpired = false;

    private boolean workIsDone = false;

    private final Queue<Element> currentElements = new ConcurrentLinkedQueue<>();

    private final Queue<String> currentVacancyUrls = new ConcurrentLinkedQueue<>();

    private final Queue<Vacancy> currentVacancies = new ConcurrentLinkedQueue<>();

    private Document doc;

    private final VacancyService vacancyService = new VacancyServiceImpl();

    private long startTime;

    public GJParser(long periodInSec) {
        this.periodInMillis = periodInSec * 1000;
    }


    public void start() throws InterruptedException {
        startTime = System.currentTimeMillis();

        final Thread urlElementParser = new Thread(this::parseUrlElements);
        final Thread urlParser = new Thread(this::parseUrls);
        final Thread vacancyParser = new Thread(this::parseVacancies);
        final Thread vacancySaver = new Thread(this::saveVacancies);

        urlElementParser.setPriority(1);
        urlParser.setPriority(2);
        vacancyParser.setPriority(7);
        vacancySaver.setPriority(7);

        urlElementParser.start();
        urlParser.start();
        vacancyParser.start();
        vacancySaver.start();

        log.info("all threads start work");

        urlElementParser.join();
        urlParser.join();
        vacancyParser.join();
        vacancySaver.join();
    }

    private void parseUrlElements() {
        while (!workIsDone && !timeExpired) {
            try {
                doc = Jsoup.connect(getNextPageUrl()).get();
            } catch (IOException e) {
                log.error(e.getMessage());
                setWorkIsDone();
                return;
            }
            //#serplist
            for (Element e : doc.select("#serplist > li > a")) {
                currentElements.offer(e);
            }
            log.info("parsed {} page", currentPage - 1);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
                continue;
            }
            checkTime();
        }
        log.info("urlElementParser finish work");
    }


    private void parseUrls() {
        while (!workIsDone && (!timeExpired || !currentElements.isEmpty())) {
            if (currentElements.isEmpty()){
                continue;
            }
            final Element elemWithUrl = currentElements.poll();

            final String url = elemWithUrl.attr("href");
            if (url != null){
                currentVacancyUrls.offer(url);
            }
            checkTime();
        }
        log.info("urlParser finish work");
    }

    private void parseVacancies() {
        while (!workIsDone && !timeExpired) {
            if (currentVacancyUrls.isEmpty()) {
                continue;
            }
            final String url = currentVacancyUrls.poll();
            Document vdoc;
            try {
                vdoc = Jsoup.connect(baseUrl + url).get();
            } catch (IOException e) {
                log.warn("vacancyParser cant connect to url - {}", baseUrl + url);
                continue;
            }
            try {
                final String name = vdoc.select(NAME_SELECTOR).text();
                final String city = vdoc.select(CITY_SELECTOR).text();
                final String companyName = vdoc.select(COMPANY_NAME_SELECTOR).text();
                final String description = vdoc.select(DESCRIPTION_SELECTOR).text().replace("Описание вакансии ", "");
                final String[] dateString = vdoc.select(DATE_SELECTOR).text().split(" ");
                final Date created = Date.valueOf(String.format("%d-%d-%d",
                        OffsetDateTime.now().getYear(),
                        getNumberOfMonth(dateString[1]),
                        Integer.parseInt(dateString[0])));
                final GJExperience experience = GJExperience.parse(vdoc.select(EXPERIENCE_SELECTOR).text());
                final GJSalary salary = GJSalary.parse(vdoc.select(SALARY_SELECTOR).text());

                final Vacancy vacancy = new Vacancy();
                vacancy.setName(name);
                vacancy.setCity(city);
                vacancy.setCompanyName(companyName);
                vacancy.setDescription(description);
                vacancy.setCreated(created);
                vacancy.setExperienceFrom(experience.from());
                vacancy.setExperienceTo(experience.to());
                vacancy.setSalaryFrom(salary.from());
                vacancy.setSalaryTo(salary.to());
                vacancy.setCurrency(salary.currency());
                currentVacancies.offer(vacancy);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            checkTime();
        }
        log.info("vacancyParser finish work");
    }

    private void saveVacancies() {
        while (!workIsDone && !timeExpired) {
            if (currentVacancies.isEmpty()) {
                continue;
            }
            Vacancy vacancy = currentVacancies.poll();
            if (!vacancyService.save(vacancy)) {
                if (++currentCountOfRepeatedVacancies > MAX_COUNT_OF_REPEATED_VACANCIES){
                    log.info("parser find too much vacancies had saved, set work as done and finish work");
                    setWorkIsDone();
                }
            }
            log.info("save vacancy with name={}", vacancy.getName());
            checkTime();
        }
        log.info("vacancySaver finish work");
    }

    private void checkTime() {
        if (System.currentTimeMillis() - startTime > periodInMillis) {
            log.info("work time expired");
            timeExpired = true;
        }
    }

    private void setWorkIsDone() {
        workIsDone = true;
    }

    private String getNextPageUrl() {
        if (currentPage == 0) {
            currentPage++;
            return vacanciesUrl;
        } else {
            return String.format("%s/%d", vacanciesUrl, currentPage++);
        }
    }

}
