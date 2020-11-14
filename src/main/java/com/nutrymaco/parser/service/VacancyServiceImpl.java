package com.nutrymaco.parser.service;

import com.nutrymaco.parser.model.tables.pojos.Vacancy;
import com.nutrymaco.parser.repository.VacancyRepository;
import com.nutrymaco.parser.repository.VacancyRepositoryImpl;

import java.util.Optional;

public class VacancyServiceImpl implements VacancyService {

    VacancyRepository vacancyRepository = new VacancyRepositoryImpl();

    @Override
    public boolean save(Vacancy vacancy) {
        Optional<Vacancy> saved = vacancyRepository.findFirstByNameAndCompanyAndCity(
                vacancy.getName(), vacancy.getCompanyName(), vacancy.getCity());
        if (saved.isEmpty()) {
            vacancyRepository.save(vacancy);
            return true;
        }
        return false;
    }
}
