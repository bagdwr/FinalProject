package com.example.finalproject.Service;

import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.Library;
import com.example.finalproject.Repository.LibraryRepository;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LibraryService {
    @EJB
    LibraryRepository libraryRepository;

    @Test
    public Library createLibrary(String name, String address) {
        if (!name.isEmpty() && !address.isEmpty()){
            Library library=new Library(null,name,address);
            return libraryRepository.createLibrary(library);
        }
        return null;
    }
}
