package com.example.finalproject.Controller;

import com.example.finalproject.Model.Book;
import com.example.finalproject.Model.ErrorMessage;
import com.example.finalproject.Model.Library;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.BookService;
import com.example.finalproject.Service.LibraryService;
import com.example.finalproject.Service.UserService;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@PermitAll
@Path(value = "/lib")
public class LibraryController {
    @EJB
    LibraryService libraryService;

    @EJB
    BookService bookService;

    //http://localhost:8080/final/api/lib/getLibById/1
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getLibById/{id}")
    public Response getLibById(@PathParam(value = "id")Integer id){
        Library library= libraryService.getLibById(id);
        if (library!=null){
            return Response.ok()
                    .entity(library)
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage(400,"Library getting error!"))
                .build();
    }

    //http://localhost:8080/final/api/lib/getBookById/1
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getBookById/{id}")
    public Response getBookById(@PathParam(value = "id")Integer id){
        Book book= bookService.getBookById(id);
        if (book!=null){
            return Response.ok()
                    .entity(book)
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage(400,"Library getting error!"))
                .build();
    }

    //http://localhost:8080/final/api/lib/createLibraryUserJoint/1/4
    @PermitAll
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createLibraryUserJoint/{lib_id}/{user_id}")
    public Response createLibraryUserJoint(
            @PathParam(value = "lib_id")Integer lib_id,
            @PathParam(value = "user_id")Integer user_id
    ){
        if (lib_id!=null && user_id!=null){
            libraryService.createLibraryUserJoint(lib_id,user_id);
            return  Response.ok()
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage(400,"Library user joint creation error"))
                .build();
    }

}
