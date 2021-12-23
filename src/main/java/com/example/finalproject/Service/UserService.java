package com.example.finalproject.Service;

import com.example.finalproject.Model.User;
import com.example.finalproject.Repository.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Stateless
public class UserService {
    @EJB
    UserRepository userRepository;

    public User createUser(String name,String email,String birthday,String password){
        if (!(name.isEmpty() && birthday.isEmpty() && email.isEmpty() && password.isEmpty())){
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("d/MM/yyyy");
            User user=new User(null,name,email, LocalDate.parse(birthday,formatter),password,null);
            return userRepository.createUser(user);
        }else {
            return null;
        }
    }

    public User getUserByEmail(String email){
        if (!email.isEmpty()){
            return getUserByEmail(email);
        }else {
            return null;
        }
    }
}
