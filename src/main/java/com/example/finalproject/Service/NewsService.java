package com.example.finalproject.Service;

import com.example.finalproject.Interceptor.NewsServiceInterceptor;
import com.example.finalproject.Model.Genre;
import com.example.finalproject.Model.News;
import com.example.finalproject.Repository.NewsRepository;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless
public class NewsService {
    @EJB
    NewsRepository newsRepository;

    @EJB
    GenreService genreService;

    @Test
    @Interceptors({NewsServiceInterceptor.class})
    public News createNews(String title, String message, Integer genderId){
        if (!title.isEmpty() && !message.isEmpty() && genderId!=null){
            Genre genre=genreService.getGenreById(genderId);
            News news=new News(null,title,message,genre);
            return newsRepository.createNews(news);
        }
        return null;
    }

    @Test
    @Interceptors({NewsServiceInterceptor.class})
    public News getNewsById(Integer new_id){
        if (new_id!=null){
            return newsRepository.getNewsById(new_id);
        }
        return null;
    }

    @Test
    @Interceptors({NewsServiceInterceptor.class})
    public void deleteNewsById(Integer id) {
        if (id!=null){
            newsRepository.deleteNewsById(id);
        }
    }

    @Test
    @Interceptors({NewsServiceInterceptor.class})
    public List<News> getAllNews() {
        return newsRepository.getAllNews();
    }
}
