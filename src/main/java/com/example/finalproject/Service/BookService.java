package com.example.finalproject.Service;

import com.example.finalproject.Model.Book;
import com.example.finalproject.Repository.BookRepository;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class BookService {
    @EJB
    BookRepository bookRepository;

    @Test
    public Book createBook(String name, String author, String genre) {
        if (!name.isEmpty() && !author.isEmpty() && !genre.isEmpty()){
            Book book=new Book(null,name,author,genre);
            return bookRepository.createBook(book);
        }
        return null;
    }
}
