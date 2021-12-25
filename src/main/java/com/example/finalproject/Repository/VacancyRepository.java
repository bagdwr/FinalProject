package com.example.finalproject.Repository;

import com.example.finalproject.Interceptor.UserServiceInterceptor;
import com.example.finalproject.Interceptor.VacancyServiceInterceptor;
import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.Vacancy;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateless
public class VacancyRepository {
    @Inject
    private DBmanager dBmanager;

    @Test
    @Interceptors({VacancyServiceInterceptor.class})
    public Vacancy createVacancy(Vacancy vacancy) {
        return dBmanager.createVacancy(vacancy);
    }

    @Test
    @Interceptors({VacancyServiceInterceptor.class})
    public Vacancy getVacById(Integer vac_id) {
        return dBmanager.getVacById(vac_id);
    }

    @Test
    @Interceptors({VacancyServiceInterceptor.class})
    public Vacancy editVacancy(Vacancy vacancy) {
        return dBmanager.editVacancy(vacancy);
    }

    @Test
    @Interceptors({VacancyServiceInterceptor.class})
    public void deleteVacancy(Integer id) {
        dBmanager.deleteVacancy(id);
    }
}
