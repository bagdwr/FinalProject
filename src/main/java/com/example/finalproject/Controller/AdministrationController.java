package com.example.finalproject.Controller;

import com.example.finalproject.Model.Book;
import com.example.finalproject.Model.ErrorMessage;
import com.example.finalproject.Model.Library;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.BookService;
import com.example.finalproject.Service.LibraryService;
import com.example.finalproject.Service.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
}
