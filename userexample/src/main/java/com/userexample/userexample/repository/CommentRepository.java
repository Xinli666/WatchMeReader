package com.userexample.userexample.repository;

import com.userexample.userexample.bean.CClass;
import com.userexample.userexample.bean.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, CClass> {
    @Query(value = "select * from Comment where name = ?1 and news = ?2", nativeQuery = true)
    Optional<Comment> findComment(String name, String news);

    @Query(value = "select * from Comment where news = ?1 order by number desc", nativeQuery = true)
    List<Comment> findByNews(String news);

    @Query(value = "select * from Comment order by number desc", nativeQuery = true)
    List<Comment> orderByNumber();

    @Modifying
    @Transactional
    @Query(value = "insert into Comment(name, news, number, comment) value(?1, ?2, ?3, ?4)", nativeQuery = true)
    void addComment(String name, String news, Integer number, String comment);

    @Modifying
    @Transactional
    @Query(value = "update Comment u set u.number = ?3, u.comment = ?4 where u.name = ?1 and u.news = ?2", nativeQuery = true)
    void updateComment(String name, String news, Integer number, String comment);

    @Query(value = "select * from Comment where name = ?1", nativeQuery = true)
    List<Comment> findByName(String name);

    @Query(value = "select count(*) from Comment where name = ?1", nativeQuery = true)
    Integer getCol(String name);

    @Modifying
    @Transactional
    @Query(value = "update Comment set number = number + 1 where name = ?1 and news = ?2")
    void addNumber(String name, String news);
}
