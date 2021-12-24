package com.example.finalproject.Controller;

import com.example.finalproject.Model.ErrorMessage;
import com.example.finalproject.Model.Genre;
import com.example.finalproject.Model.News;
import com.example.finalproject.Service.GenreService;
import com.example.finalproject.Service.NewsService;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@PermitAll
@Path(value = "/business")
public class BusinessController {
    @EJB
    GenreService genreService;

    @EJB
    NewsService newsService;

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/getNewsById/{id}")
    public Response getNewsById(@PathParam(value = "id") Integer id){
        if (id!=null){
            News news=newsService.getNewsById(id);
            if (news!=null){
                return Response.ok()
                        .entity(news)
                        .build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(400,"EMPTY ID"))
                .build();
    }

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/getGenreById/{id}")
    public Response getGenreById(@PathParam(value = "id") Integer id){
        if (id!=null){
            Genre genre=genreService.getGengreById(id);
            if (genre!=null){
                return Response.ok()
                        .entity(genre)
                        .build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(400,"EMPTY ID"))
                .build();
    }
}
