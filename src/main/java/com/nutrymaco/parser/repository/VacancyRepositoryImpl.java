package com.nutrymaco.parser.repository;

import com.nutrymaco.parser.config.DBConfiguration;
import com.nutrymaco.parser.model.tables.pojos.Vacancy;
import com.nutrymaco.parser.model.tables.records.VacancyRecord;
import org.jooq.DSLContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.nutrymaco.parser.model.Tables.VACANCY;

public class VacancyRepositoryImpl implements VacancyRepository {
    static DBConfiguration dbConfiguration = new DBConfiguration();
    static DSLContext dsl = dbConfiguration.dslContext();


    @Override
    public void save(Vacancy vacancy) {
        dsl.insertInto(VACANCY)
                .set(dsl.newRecord(VACANCY, vacancy))
                .execute();
    }

    @Override
    public boolean exists(Vacancy vacancy) {
        return false;
    }

    @Override
    public Optional<Vacancy> findFirstByNameAndCompanyAndCity(String name, String company, String city) {
        List<Vacancy> vacancyList = dsl.select()
                .from(VACANCY)
                .where(VACANCY.NAME.eq(name))
                .and(VACANCY.COMPANY_NAME.eq(company))
                .and(VACANCY.CITY.eq(city))
                .fetchInto(Vacancy.class);
        if (vacancyList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(vacancyList.get(0));
        }
    }
}
