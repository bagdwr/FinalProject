package com.example.finalproject.Controller;

import com.example.finalproject.Model.JobCenter;
import com.example.finalproject.Model.Vacancy;

import javax.ejb.EJB;

public class JobSeekerController {
    @EJB
    JobCenter jobCenter;

    @EJB
    Vacancy vacancy;
}
