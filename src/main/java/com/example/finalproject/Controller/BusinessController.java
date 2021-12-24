package com.example.finalproject.Controller;

import com.example.finalproject.Model.ErrorMessage;
import com.example.finalproject.Model.Genre;
import com.example.finalproject.Model.News;
import com.example.finalproject.Service.GenreService;
import com.example.finalproject.Service.NewsService;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

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
            Genre genre=genreService.getGenreById(id);
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

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/getAllNews")
    public Response getAllNews(){
        List<News> newsList=newsService.getAllNews();
        if (!newsList.isEmpty()){
            return Response.ok()
                    .entity(newsList)
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(400,"Empty list"))
                .build();
    }

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/getAllNewsByGenre")
    public Response getAllNewsByGenre(@QueryParam(value = "genreId")Integer id){
            if (id!=null){
                List<News> newsList=newsService.getAllNews().stream().filter(news->news.getGenre().getId().equals(id)).collect(Collectors.toList());
                if (!newsList.isEmpty()){
                    return Response.ok()
                            .entity(newsList)
                            .build();
                }
            }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(400,"Empty list"))
                .build();
    }
}
