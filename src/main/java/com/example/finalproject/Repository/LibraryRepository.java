package com.example.finalproject.Repository;

import com.example.finalproject.Model.Book;
import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.Library;
import com.example.finalproject.Model.User;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LibraryRepository {
    @Inject
    private DBmanager dBmanager;

    @Test
    public Library createLibrary(Library library) {
        return dBmanager.createLibrary(library);
    }

    @Test
    public Library getLibByID(Integer id){
        return dBmanager.getLibByID(id);
    }

    @Test
    public Library createLibraryBookJoint(Library library, Book book) {
        return dBmanager.createLibraryBookJoint(library,book);
    }

    @Test
    public void createLibraryUserJoint(Library library, User user) {
        dBmanager.createLibraryUserJoint(library,user);
    }
}
