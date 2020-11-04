package com.nutrymaco.parser.job;

import com.nutrymaco.parser.model.tables.pojos.Vacancy;
import com.nutrymaco.parser.pojo.GJExperience;
import com.nutrymaco.parser.pojo.GJSalary;
import com.nutrymaco.parser.service.VacancyService;
import com.nutrymaco.parser.service.VacancyServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.sql.Array;
import java.time.OffsetDateTime;
import java.sql.Date;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

import static com.nutrymaco.parser.util.DateUtil.getNumberOfMonth;


public class GJParser {

    private final static String NAME_SELECTOR = "#body > section > article.row.vacancy > section > header > h1";

    private final static String CITY_SELECTOR = "#body > section > article.row.vacancy > section > header > div.location";

    private final static String COMPANY_NAME_SELECTOR = "#body > section > article.row.vacancy > section > header > h5 > a";

    private final static String DESCRIPTION_SELECTOR = "#body > section > article.row.vacancy > section > div.description";

    private final static String DATE_SELECTOR = "#body > section > article.row.vacancy > section > header > div.time";

    private final static String EXPERIENCE_SELECTOR = "#body > section > article.row.vacancy > section > header > div.jobinfo > span.salary";

    private final static String SALARY_SELECTOR = "#body > section > article.row.vacancy > section > header > div.jobinfo > span.salary";

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

    private final VacancyService vacancyService = VacancyServiceImpl.getInstance();

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

        urlElementParser.start();
        urlParser.start();
        vacancyParser.start();
        vacancySaver.start();

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
                e.printStackTrace();
                return;
            }
            //#serplist
            for (Element e : doc.select("#serplist > li > a")) {
                currentElements.offer(e);
            }
            checkTime();
        }
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
    }

    private void parseVacancies() {
        while (!workIsDone && (!timeExpired || !currentVacancyUrls.isEmpty())) {
            if (currentVacancyUrls.isEmpty()) {
                continue;
            }
            final String url = currentVacancyUrls.poll();
            Document vdoc = null;
            try {
                vdoc = Jsoup.connect(baseUrl + url).get();
            } catch (IOException e) {
                System.out.println(baseUrl + url);
                e.printStackTrace();
                continue;
            }
            final String name = vdoc.select(NAME_SELECTOR).text();
            final String city = vdoc.select(CITY_SELECTOR).text();
            final String companyName = vdoc.select(COMPANY_NAME_SELECTOR).text();
            final String description = vdoc.select(DESCRIPTION_SELECTOR).text();
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
            checkTime();
        }
    }

    private void saveVacancies() {
        while (!workIsDone && (!timeExpired || !currentVacancies.isEmpty())) {
            if (currentVacancies.isEmpty()) {
                continue;
            }
            Vacancy vacancy = currentVacancies.poll();
            if (!vacancyService.save(vacancy)) {
                setWorkIsDone();
            }
            System.out.println(vacancy);
            checkTime();
        }
    }

    private void checkTime() {
        if (System.currentTimeMillis() - startTime > periodInMillis) {
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
