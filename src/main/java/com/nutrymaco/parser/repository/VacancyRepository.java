package com.nutrymaco.parser.repository;

import com.nutrymaco.parser.model.tables.pojos.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository {

    void save(Vacancy vacancy);

    Optional<Vacancy> findFirstByNameAndCompanyAndCity(String name, String company, String city);

}
