package com.example.finalproject.Repository;

import com.example.finalproject.Model.Book;
import com.example.finalproject.Model.DBmanager;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BookRepository {
    @Inject
    private DBmanager dBmanager;

    @Test
    public Book createBook(Book book) {
        return dBmanager.createBook(book);
    }
}
