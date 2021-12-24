package com.example.finalproject.Controller;

import com.example.finalproject.Model.ErrorMessage;
import com.example.finalproject.Model.JobCenter;
import com.example.finalproject.Model.Vacancy;
import com.example.finalproject.Service.JobCenterService;
import com.example.finalproject.Service.VacancyService;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@PermitAll
@Path("/jobseeker")
public class JobSeekerController {
    @EJB
    JobCenterService jobCenterService;

    @EJB
    VacancyService vacancyService;

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/getJobCenterById/{id}")
    public Response getJobById(@PathParam(value = "id")Integer id){
        if (id!=null){
            JobCenter jobCenter= jobCenterService.getJobCenterById(id);
            if (jobCenter!=null){
                return Response.ok()
                        .entity(jobCenter)
                        .build();
            }
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(404,"Empty id"))
                .build();
    }

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/getVacById/{id}")
    public Response getVacById(@PathParam(value = "id")Integer id){
        if (id!=null){
            Vacancy vacancy= vacancyService.getVacById(id);
            if (vacancy!=null){
                return Response.ok()
                        .entity(vacancy)
                        .build();
            }
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(404,"Empty id"))
                .build();
    }
}
