package com.example.finalproject.Controller;

import com.example.finalproject.Model.*;
import com.example.finalproject.Service.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Path(value = "/admin")
@PermitAll
public class AdministrationController {
    @EJB
    UserService userService;

    @EJB
    BookService bookService;

    @EJB
    LibraryService libraryService;

    @EJB
    NewsService newsService;

    @EJB
    GenreService genreService;

    @EJB
    VacancyService vacancyService;

    @EJB
    JobCenterService jobCenterService;

    //region USER
    //http://localhost:8080/final/api/admin/signUp
    @PermitAll
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/signUp")
    public Response createUser(
            @FormParam(value = "name") String name,
            @FormParam(value = "email")String email,
            @FormParam(value = "birthday")String birthday,
            @FormParam(value = "password")String password
    ){
        User user= userService.createUser(name,email,birthday,password);
        if (user!=null){
            return Response.ok()
                    .entity(user)
                    .build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessage(400,"User creation error!"))
                    .build();
        }
    }

    //http://localhost:8080/final/api/admin/getUserById/4
    @GET
    @RolesAllowed({"ROLE_ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/getUserById/{id}")
    public Response getUserById(
            @PathParam(value = "id") Integer id
    ){
        if (id!=null){
            User user=userService.getUserByID(id);
            if (user!=null){
                return Response.ok()
                        .entity(user)
                        .build();
            }else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(new ErrorMessage(204,"User not found!"))
                        .build();
            }
        }else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessage(400,"Enter ID!"))
                    .build();
        }
    }

    //http://localhost:8080/final/api/admin/getUserByEmail?email=Daur@gmail.com
    @GET
    @RolesAllowed({"ROLE_ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/getUserByEmail")
    public Response getUserById(
            @QueryParam(value = "email") String email
    ){
        if (!email.isEmpty()){
            User user=userService.getUserByEmail(email);
            if (user!=null){
                return Response.ok()
                        .entity(user)
                        .build();
            }else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(new ErrorMessage(500,"User not found!"))
                        .build();
            }
        }else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessage(400,"Enter EMAIL!"))
                    .build();
        }
    }

    //http://localhost:8080/final/api/admin/getAllUsers
    @GET
    @RolesAllowed({"ROLE_ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/getAllUsers")
    public Response getAllUsers(){
        List<User>users=userService.getAllUsers();
        if (!users.isEmpty()){
            return Response.ok()
                    .entity(users)
                    .build();
        }else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorMessage(500,"EMPTY LIST"))
                    .build();
        }
    }

    //http://localhost:8080/final/api/admin/editUser/1
    @PUT
    @RolesAllowed({"ROLE_ADMIN"})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/editUser/{id}")
    public Response editUser(
            @PathParam(value = "id") Integer id,
            @FormParam(value = "name")String name,
            @FormParam(value = "password")String password,
            @FormParam(value = "birthday")String birthday
    ){
        if (id!=null && !name.isEmpty() && !name.isEmpty() && !password.isEmpty() && !birthday.isEmpty()){
            User user=userService.getUserByID(id);
            if (user!=null){
                return Response.ok()
                        .entity(userService.editUser(user,name,birthday,password))
                        .build();
            }
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage(400,"User edit error"))
                .build();
    }

    //http://localhost:8080/final/api/admin/getAllUsersByAgeHigherThan/10
    @GET
    @RolesAllowed({"ROLE_ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/getAllUsersByAgeHigherThan/{age}")
    public Response getAllUserHigherThan(@PathParam(value = "age")Integer age){
        if (age!=null){
            List<User>users=userService.getAllUsersHigherThan(age);
            if (!users.isEmpty()){
                return Response.ok()
                        .entity(users)
                        .build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorMessage(400,"List is empty"))
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage(400,"AGE is empty"))
                .build();
    }
    //endregion

    //region Library
    //http://localhost:8080/final/api/admin/createBook
    @RolesAllowed({"ROLE_ADMIN"})
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/createBook")
    public Response createBook(
            @FormParam(value = "name") String name,
            @FormParam(value = "author")String author,
            @FormParam(value = "genre")String genre
    ){
        Book book= bookService.createBook(name,author,genre);
        if (book!=null){
            return Response.ok()
                    .entity(book)
                    .build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessage(400,"Book creation error!"))
                    .build();
        }
    }

    //http://localhost:8080/final/api/admin/createLibrary
    @RolesAllowed({"ROLE_ADMIN"})
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/createLibrary")
    public Response createLibrary(
            @FormParam(value = "name") String name,
            @FormParam(value = "address")String address
    ){
        Library library=libraryService.createLibrary(name,address);
        if (library!=null){
            return Response.ok()
                    .entity(library)
                    .build();
        }else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorMessage(400,"Library creation error!"))
                    .build();
        }
    }

    //http://localhost:8080/final/api/admin/createLibraryBookJoint/1/4
    @RolesAllowed({"ROLE_ADMIN"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/createLibraryBookJoint/{lib_id}/{book_id}")
    public Response createLibraryBookJoint(
            @PathParam(value = "lib_id")Integer lib_id,
            @PathParam(value = "book_id")Integer book_id
    ){
        Library library=libraryService.createLibraryBookJoint(lib_id,book_id);
        if (library!=null){
            return Response.ok()
                    .entity(library)
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage(400,"Adding book to library error!"))
                .build();
    }
    //endregion

    //region News
    @RolesAllowed({"ROLE_ADMIN"})
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/createGenre")
    public Response createGenre(
            @FormParam(value = "name") String name
    ){
        if (!name.isEmpty()){
            Genre genre=genreService.createGenre(name);
            if (genre!=null){
                return Response.ok()
                        .entity(genre)
                        .build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorMessage(400,"Genre creation error"))
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage(400,"Genre creation error"))
                .build();
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/createNews")
    public Response createNews(
            @FormParam(value = "title") String title,
            @FormParam(value = "message")String message,
            @FormParam(value = "genre_id")Integer genre_id)
    {
        if (!title.isEmpty() && !message.isEmpty() && genre_id!=null){
            News news=newsService.createNews(title,message,genre_id);
            if (news!=null){
                return Response.ok()
                        .entity(news)
                        .build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorMessage(400,"News creation error"))
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage(400,"News creation error"))
                .build();
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/deleteNews")
    public Response deleteNews(
           @QueryParam(value = "id") Integer id
    ){
        if (id!=null){
            News news=newsService.getNewsById(id);
            if (news!=null){
                newsService.deleteNewsById(id);
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage(400,"Genre creation error"))
                .build();
    }
    //endregion

    //region JobSeeker

    //http://localhost:8080/final/api/admin/createVacancy
    @RolesAllowed({"ROLE_ADMIN"})
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/createVacancy")
    public Response createVacancy(
            @FormParam(value = "name")String name,
            @FormParam(value = "salary")Integer salary,
            @FormParam(value = "points")String points
            ){
        Vacancy vacancy=vacancyService.createVacancy(name,salary,points);
        if (vacancy!=null){
            return Response.ok()
                    .entity(vacancy)
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(400,"Vacancy creation error"))
                .build();
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/createJobCenter")
    public Response createJobCenter(
            @FormParam(value = "name")String name,
            @FormParam(value = "location")String location
    ){
        JobCenter jobCenter= jobCenterService.createJobcenter(name,location);
        if (jobCenter!=null){
            return Response.ok()
                    .entity(jobCenter)
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(400,"Job center creation error"))
                .build();
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/сreateJobVacancyJoint/{job_id}/{vac_id}")
    public Response сreateJobVacancyJoint(
            @PathParam(value = "job_id") Integer job_id,
            @PathParam(value = "vac_id") Integer vac_id
    ){
        JobCenter jobCenter=jobCenterService.createJobVacJoint(job_id,vac_id);
        if (jobCenter!=null){
            return Response.ok()
                    .entity(jobCenter)
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(400,"JobCenterVacancyJoint creation error"))
                .build();
    }
    //endregion
}
