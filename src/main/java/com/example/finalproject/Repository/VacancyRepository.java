package com.example.finalproject.Repository;

import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.Vacancy;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class VacancyRepository {
    @Inject
    private DBmanager dBmanager;

    @Test
    public Vacancy createVacancy(Vacancy vacancy) {
        return dBmanager.createVacancy(vacancy);
    }

    @Test
    public Vacancy getVacById(Integer vac_id) {
        return dBmanager.getVacById(vac_id);
    }

    @Test
    public Vacancy editVacancy(Vacancy vacancy) {
        return dBmanager.editVacancy(vacancy);
    }

    @Test
    public void deleteVacancy(Integer id) {
        dBmanager.deleteVacancy(id);
    }
}
