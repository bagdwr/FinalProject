package com.example.finalproject.Repository;

import com.example.finalproject.Interceptor.UserInterceptor;
import com.example.finalproject.Interceptor.UserServiceInterceptor;
import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.User;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless
public class UserRepository {
    @Inject
    private DBmanager dBmanager;

    @Test
    @Interceptors({UserServiceInterceptor.class})
    public User createUser(User user){
        return dBmanager.createUser(user);
    }

    @Test
    @Interceptors({UserServiceInterceptor.class})
    public User getUserByEmail(String email){
        return dBmanager.getUserByEmail(email);
    }

    @Test
    @Interceptors({UserServiceInterceptor.class})
    public User getUserByID(Integer id) {
        return dBmanager.getUserByID(id);
    }

    @Test
    @Interceptors({UserServiceInterceptor.class})
    public List<User> getAllUsers() {
        return dBmanager.getAllUsers();
    }

    @Test
    @Interceptors({UserServiceInterceptor.class})
    public User editUser(User user) {
        return dBmanager.editUser(user);
    }
}
