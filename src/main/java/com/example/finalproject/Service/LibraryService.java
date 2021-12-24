package com.example.finalproject.Service;

import com.example.finalproject.Model.Book;
import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.Library;
import com.example.finalproject.Model.User;
import com.example.finalproject.Repository.LibraryRepository;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LibraryService {
    @EJB
    LibraryRepository libraryRepository;

    @EJB
    BookService bookService;

    @EJB
    UserService userService;

    @Test
    public Library createLibrary(String name, String address) {
        if (!name.isEmpty() && !address.isEmpty()){
            Library library=new Library(null,name,address,null);
            return libraryRepository.createLibrary(library);
        }
        return null;
    }

    @Test
    public Library getLibById(Integer lib_id){
        if (lib_id!=null){
            return libraryRepository.getLibByID(lib_id);
        }
        return null;
    }

    @Test
    public Library createLibraryBookJoint(Integer lib_id,Integer book_id){
        if (lib_id!=null && book_id!=null){
            Library library=getLibById(lib_id);
            Book book= bookService.getBookById(book_id);
            if (library!=null && book!=null){
                return libraryRepository.createLibraryBookJoint(library,book);
            }
        }
        return null;
    }

    @Test
    public void createLibraryUserJoint(Integer lib_id, Integer user_id){
        if (lib_id!=null && user_id!=null){
            User user= userService.getUserByID(user_id);
            Library library=getLibById(lib_id);
            if (user!=null && library!=null){
                libraryRepository.createLibraryUserJoint(library,user);
            }
        }
    }
}
