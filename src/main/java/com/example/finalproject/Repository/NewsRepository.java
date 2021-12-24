package com.example.finalproject.Repository;

import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.News;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class NewsRepository {
    @Inject
    private DBmanager dBmanager;

    @Test
    public News createNews(News news){
        return dBmanager.createNews(news);
    }

    @Test
    public News getNewsById(Integer new_id) {
        return dBmanager.getNewsById(new_id);
    }

    @Test
    public void deleteNewsById(Integer id) {
        dBmanager.deleteNewsById(id);
    }

    @Test
    public List<News> getAllNews() {
        return dBmanager.getAllNews();
    }
}
