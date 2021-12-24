package com.example.finalproject.Service;

import com.example.finalproject.Model.JobCenter;
import com.example.finalproject.Model.Vacancy;
import com.example.finalproject.Repository.JobCenterRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class JobCenterService {
    @EJB
    JobCenterRepository jobCenterRepository;

    @EJB
    VacancyService vacancyService;

    public JobCenter createJobcenter(String name, String location){
        if (!name.isEmpty() && !location.isEmpty()){
            JobCenter jobCenter=new JobCenter(null,name,location,null);
            return jobCenterRepository.createJobCenter(jobCenter);
        }
        return null;
    }

    public JobCenter getJobCenterById(Integer id){
        if (id!=null){
            return jobCenterRepository.getJobCenterById(id);
        }
        return null;
    }

    public JobCenter createJobVacJoint(Integer job_id, Integer vac_id) {
        if (job_id!=null && vac_id!=null){
            Vacancy vacancy=vacancyService.getVacById(vac_id);
            JobCenter jobCenter=getJobCenterById(job_id);
            return jobCenterRepository.createJobVacJoint(job_id,vac_id);
        }
        return null;
    }
}
