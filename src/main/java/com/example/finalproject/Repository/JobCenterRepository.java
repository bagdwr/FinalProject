package com.example.finalproject.Repository;

import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.JobCenter;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class JobCenterRepository {
    @Inject
    private DBmanager dBmanager;

    @Test
    public JobCenter createJobCenter(JobCenter jobCenter){
        return dBmanager.createJobCenter(jobCenter);
    }

    @Test
    public JobCenter getJobCenterById(Integer job_id){
        return dBmanager.getJobCenterById(job_id);
    }

    public JobCenter createJobVacJoint(Integer job_id, Integer vac_id) {
        return dBmanager.createJobVacJoint(job_id,vac_id);
    }
}
