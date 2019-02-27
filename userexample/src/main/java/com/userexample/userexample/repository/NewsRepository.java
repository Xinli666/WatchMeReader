package com.userexample.userexample.repository;

import com.userexample.userexample.bean.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    @Query(value = "select * from News a limit ?1, ?2", nativeQuery = true)
    List<News> page(int p1, int p2);

    @Query(value = "select * from News a where a.tagID = ?1 limit ?2, ?3", nativeQuery = true)
    List<News> pageByTag(Integer tagID, int p1, int p2);

    @Modifying
    @Transactional
    @Query(value = "insert into News(newsID, time, title, summary, news_address, img_address, heat, tagID) value(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    void addNews(int userID, String time, String title, String summary, String news_address, String img_address, int heat, int tagID);

    @Modifying
    @Transactional
    @Query(value = "update News u set u.time = ?2, u.title = ?3, u.summary = ?4, u.news_address = ?5, u.img_address = ?6, u.heat = ?7, u.tagID = ?8 where u.newsID = ?1", nativeQuery = true)
    void updateNews(int userID, String time, String title, String summary, String news_address, String img_address, int heat, int tagID);

    @Query(value = "select * from News a where a.time = ?1", nativeQuery = true)
    List<News> findByTime(String time);

    @Query("select a from News a where a.title = ?1")
    List<News> findByTitle(String title);

    @Query("select a from News a where a.newsID = ?1")
    List<News> queryByID(Integer id);

    @Modifying
    @Transactional
    @Query(value = "update News set newsID = newsID - 1 where newsID > ?1", nativeQuery = true)
    void updateDelete(int id);

    @Modifying
    @Transactional
    @Query(value = "update News set heat = heat + 1 where newsID = ?1", nativeQuery = true)
    void addHeat(Integer newsID);

    @Query(value = "select * from News a where a.title like ?1 or a.summary like ?2", nativeQuery = true)
    List<News> search(String title, String summary);

    @Query(value = "select * from News where tagID = ?1 order by heat desc", nativeQuery = true)
    List<News> orderByHeat(Integer tagID);

    @Query(value = "select * from News where newsID = ?1", nativeQuery = true)
    News getRecordNews(Integer newsID);
}
