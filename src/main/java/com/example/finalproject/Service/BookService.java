package com.example.finalproject.Service;

import com.example.finalproject.Interceptor.BookServiceInterceptor;
import com.example.finalproject.Model.Book;
import com.example.finalproject.Model.Library;
import com.example.finalproject.Repository.BookRepository;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
public class BookService {
    @EJB
    BookRepository bookRepository;

    @Test
    @Interceptors({BookServiceInterceptor.class})
    public Book createBook(String name, String author, String genre) {
        if (!name.isEmpty() && !author.isEmpty() && !genre.isEmpty()){
            Book book=new Book(null,name,author,genre);
            return bookRepository.createBook(book);
        }
        return null;
    }

    @Test
    @Interceptors({BookServiceInterceptor.class})
    public Book getBookById(Integer id){
        if (id!=null){
            return bookRepository.getBookByID(id);
        }
        return null;
    }
}
