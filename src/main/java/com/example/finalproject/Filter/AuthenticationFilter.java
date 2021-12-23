package com.example.finalproject.Filter;


import com.example.finalproject.Model.Role;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.UserService;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    @EJB
    UserService userService;

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if (!method.isAnnotationPresent(PermitAll.class)) {
            //Access denied for all
            if (!method.isAnnotationPresent(DenyAll.class)) {//Get request headers
                        final MultivaluedMap<String, String> headers = requestContext.getHeaders();

                        //Fetch authorization header
                        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

                        //If no authorization information present; block access
                        if (!Optional.ofNullable(authorization).isPresent() || authorization.isEmpty()) {
                            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                    .entity("You cannot access this resource").build());
                            return;
                        }

                        //Get encoded username and password
                        final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
                        //Decode username and password
                        String usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword));

                        //Split username and password tokens
                        final StringTokenizer tokenizer;
                tokenizer = new StringTokenizer(usernameAndPassword, ":");
                final String username = tokenizer.nextToken();
                        final String password = tokenizer.nextToken();

                        //Verify user access
                        if (method.isAnnotationPresent(RolesAllowed.class)) {
                            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                            Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

                            //Is user valid?
                            User user=userService.getUserByEmail(username);
                            if (user!=null){
                                if (!isUserAllowed(user.getEmail(), user.getPassword(),rolesSet, user.getRoles())) {
                                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                            .entity("You cannot access this resource").build());
                                    return;
                                }
                            }

                        }
            } else {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("Access blocked for all users !!").build());
                return;
            }

        }
    }

    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet,final List<Role>roles) {
        boolean isAllowed = false;

        if (rolesSet.contains("ROLE_ADMIN")) {


            //Step 2. Verify user role
            for(Role role:roles){
                if (role.getName().equals("ROLE_ADMIN")){
                    isAllowed = true;
                    break;
                }
            }
        }
        else
        {
            //Step 2. Verify user role
            for(Role role:roles){
                if (role.getName().equals("ROLE_USER")){
                    isAllowed = true;
                }
            }
        }
        return isAllowed;
    }

}
