package com.example.finalproject.Repository;

import com.example.finalproject.Interceptor.GenreServiceInterceptor;
import com.example.finalproject.Interceptor.LibraryServiceInterceptor;
import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.Genre;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateless
public class GenreRepository {
    @Inject
    private DBmanager dBmanager;

    @Test
    @Interceptors({GenreServiceInterceptor.class})
    public Genre createGenre(Genre genre) {
        return dBmanager.createGenre(genre);
    }

    @Test
    @Interceptors({GenreServiceInterceptor.class})
    public Genre genreById(Integer genreId) {
        return dBmanager.genreById(genreId);
    }
}
