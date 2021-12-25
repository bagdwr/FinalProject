package com.example.finalproject.Repository;

import com.example.finalproject.Interceptor.JobCenterInterceptor;
import com.example.finalproject.Interceptor.JobCenterServiceInterceptor;
import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.JobCenter;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateless
public class JobCenterRepository {
    @Inject
    private DBmanager dBmanager;

    @Test
    @Interceptors({JobCenterServiceInterceptor.class})
    public JobCenter createJobCenter(JobCenter jobCenter){
        return dBmanager.createJobCenter(jobCenter);
    }

    @Test
    @Interceptors({JobCenterServiceInterceptor.class})
    public JobCenter getJobCenterById(Integer job_id){
        return dBmanager.getJobCenterById(job_id);
    }

    @Test
    @Interceptors({JobCenterServiceInterceptor.class})
    public JobCenter createJobVacJoint(Integer job_id, Integer vac_id) {
        return dBmanager.createJobVacJoint(job_id,vac_id);
    }
}
