package com.example.finalproject.Controller;

import com.example.finalproject.Model.ErrorMessage;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.UserService;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(value = "/admin")
@PermitAll
public class AdministrationController {
    @EJB
    UserService userService;

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
            return Response.status(203)
                    .entity(new ErrorMessage(203,"User creation error!"))
                    .build();
        }
    }
}
