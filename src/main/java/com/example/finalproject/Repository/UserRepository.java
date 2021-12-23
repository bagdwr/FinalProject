package com.example.finalproject.Repository;

import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.User;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserRepository {
    @Inject
    private DBmanager dBmanager;

    @Test
    public User createUser(User user){
        return dBmanager.createUser(user);
    }

    @Test
    public User getUserByEmail(String email){
        return dBmanager.getUserByEmail(email);
    }

    @Test
    public User getUserByID(Integer id) {
        return dBmanager.getUserByID(id);
    }

    @Test
    public List<User> getAllUsers() {
        return dBmanager.getAllUsers();
    }
}
