package com.example.finalproject.Repository;

import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.Genre;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GenreRepository {
    @Inject
    private DBmanager dBmanager;

    @Test
    public Genre createGenre(Genre genre) {
        return dBmanager.createGenre(genre);
    }

    @Test
    public Genre genreById(Integer genreId) {
        return dBmanager.genreById(genreId);
    }
}
