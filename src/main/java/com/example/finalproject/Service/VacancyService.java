package com.example.finalproject.Service;

import com.example.finalproject.Interceptor.VacancyServiceInterceptor;
import com.example.finalproject.Model.Vacancy;
import com.example.finalproject.Repository.VacancyRepository;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Stateless
public class VacancyService {
    @EJB
    VacancyRepository vacancyRepository;

    @Test
    @Interceptors({VacancyServiceInterceptor.class})
    public Vacancy createVacancy(String name, Integer salary, String points) {
        if (!name.isEmpty() && salary!=null && points!=null){
            Vacancy vacancy=new Vacancy(null,name,salary,new BigDecimal(points).setScale(2, RoundingMode.HALF_EVEN));
            return vacancyRepository.createVacancy(vacancy);
        }
        return null;
    }

    @Test
    @Interceptors({VacancyServiceInterceptor.class})
    public Vacancy getVacById(Integer vac_id) {
        if (vac_id!=null){
            return vacancyRepository.getVacById(vac_id);
        }
        return null;
    }

    @Test
    @Interceptors({VacancyServiceInterceptor.class})
    public Vacancy editVacancy(Vacancy vacancy) {
        return vacancyRepository.editVacancy(vacancy);
    }

    @Test
    @Interceptors({VacancyServiceInterceptor.class})
    public void deleteVacancy(Integer id) {
        vacancyRepository.deleteVacancy(id);
    }
}
