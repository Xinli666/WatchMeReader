package com.userexample.userexample.service;

import com.userexample.userexample.bean.News;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    List<News> page(int p1, int p2);

    List<News> pageByTag(Integer tagID, int p1, int p2);

    long getColNum();

    void addNews(int userID, String time, String title, String summary, String news_address, String img_address, int heat, int tagID);

    void updateNews(int userID, String time, String title, String summary, String news_address, String img_address, int heat, int tagID);

    List<News> findByTime(String time);

    List<News> findByTitle(String title);

    List<News> queryByID(Integer id);

    void deleteNews(News news);

    Optional<News> findByID(Integer id);

    void updateDelete(int id);

    void addHeat(Integer newsID);

    List<News> search(String title, String summary);

    List<News> orderByHeat(Integer tagID);

    News getRecordNews(Integer newsID);
}
