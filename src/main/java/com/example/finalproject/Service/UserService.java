package com.example.finalproject.Service;

import com.example.finalproject.Interceptor.UserInterceptor;
import com.example.finalproject.Interceptor.UserServiceInterceptor;
import com.example.finalproject.Model.User;
import com.example.finalproject.Repository.UserRepository;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserService {
    @EJB
    UserRepository userRepository;

    @Test
    @Interceptors({UserServiceInterceptor.class})
    public User createUser(String name,String email,String birthday,String password){
        if (!name.isEmpty() && !birthday.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("d/MM/yyyy");
            User user=new User(null,name,email, LocalDate.parse(birthday,formatter),password,null);
            return userRepository.createUser(user);
        }else {
            return null;
        }
    }

    @Test
    @Interceptors({UserServiceInterceptor.class})
    public User getUserByEmail(String email){
        if (!email.isEmpty()){
            return userRepository.getUserByEmail(email);
        }else {
            return null;
        }
    }

    @Test
    @Interceptors({UserServiceInterceptor.class})
    public User getUserByID(Integer id) {
        if (id!=null){
            return userRepository.getUserByID(id);
        }
        return null;
    }

    @Test
    @Interceptors({UserServiceInterceptor.class})
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Test
    @Interceptors({UserServiceInterceptor.class})
    public User editUser(User user,String name, String birthday, String password) {
        if (user!=null && !name.isEmpty() && !birthday.isEmpty() && !password.isEmpty()){
            user.setName(name);
            user.setBirthday(LocalDate.parse(birthday,DateTimeFormatter.ofPattern("d/MM/yyyy")));
            user.setPassword(password);
            return userRepository.editUser(user);
        }
        return null;
    }

    @Test
    @Interceptors({UserServiceInterceptor.class})
    public List<User> getAllUsersHigherThan(Integer age) {
        return getAllUsers().stream().filter(u->(LocalDate.now().getYear()-u.getBirthday().getYear())>age).collect(Collectors.toList());
    }
}
