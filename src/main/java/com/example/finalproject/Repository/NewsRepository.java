package com.example.finalproject.Repository;

import com.example.finalproject.Interceptor.NewsServiceInterceptor;
import com.example.finalproject.Interceptor.UserServiceInterceptor;
import com.example.finalproject.Model.DBmanager;
import com.example.finalproject.Model.News;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless
public class NewsRepository {
    @Inject
    private DBmanager dBmanager;

    @Test
    @Interceptors({NewsServiceInterceptor.class})
    public News createNews(News news){
        return dBmanager.createNews(news);
    }

    @Test
    @Interceptors({NewsServiceInterceptor.class})
    public News getNewsById(Integer new_id) {
        return dBmanager.getNewsById(new_id);
    }

    @Test
    @Interceptors({NewsServiceInterceptor.class})
    public void deleteNewsById(Integer id) {
        dBmanager.deleteNewsById(id);
    }

    @Test
    @Interceptors({NewsServiceInterceptor.class})
    public List<News> getAllNews() {
        return dBmanager.getAllNews();
    }
}
