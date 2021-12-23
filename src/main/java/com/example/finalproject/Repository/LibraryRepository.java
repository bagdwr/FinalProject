package com.example.finalproject.Repository;

import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.Library;
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
}
