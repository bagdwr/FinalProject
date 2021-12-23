package com.example.finalproject.Controller;

import com.example.finalproject.Model.ErrorMessage;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(value = "/admin")
@PermitAll
public class AdministrationController {
    @EJB
    UserService userService;

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
    //endregion
}
