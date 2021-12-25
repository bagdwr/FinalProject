package com.example.finalproject.Repository;

import com.example.finalproject.Interceptor.BookServiceInterceptor;
import com.example.finalproject.Interceptor.UserServiceInterceptor;
import com.example.finalproject.Model.Book;
import com.example.finalproject.Model.DBmanager;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateless
public class BookRepository {
    @Inject
    private DBmanager dBmanager;

    @Test
    @Interceptors({BookServiceInterceptor.class})
    public Book createBook(Book book) {
        return dBmanager.createBook(book);
    }

    @Test
    @Interceptors({BookServiceInterceptor.class})
    public Book getBookByID(Integer id){
        return dBmanager.getBookByID(id);
    }
}
