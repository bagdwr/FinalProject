package com.example.finalproject.Service;

import com.example.finalproject.Interceptor.GenreServiceInterceptor;
import com.example.finalproject.Model.Genre;
import com.example.finalproject.Repository.GenreRepository;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
public class GenreService {
    @EJB
    GenreRepository genreRepository;

    @Test
    @Interceptors({GenreServiceInterceptor.class})
    public Genre createGenre(String name){
        if (!name.isEmpty()){
            Genre genre=new Genre(null,name);
            return genreRepository.createGenre(genre);
        }
        return null;
    }

    @Test
    @Interceptors({GenreServiceInterceptor.class})
    public Genre getGenreById(Integer genreId) {
        if (genreId!=null){
            Genre genre=genreRepository.genreById(genreId);
            return genre;
        }
        return null;
    }
}
