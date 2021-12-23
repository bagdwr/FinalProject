package com.example.finalproject.Repository;

import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserRepository {
    @Inject
    private DBmanager dBmanager;

    public User createUser(User user){
        return dBmanager.createUser(user);
    }

    public User getUserByEmail(String email){
        return dBmanager.getUserByEmail(email);
    }

    public User getUserByID(Integer id) {
        return dBmanager.getUserByID(id);
    }
}
