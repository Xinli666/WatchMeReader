package com.userexample.userexample.service;

import com.userexample.userexample.bean.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAll();

    Optional<Comment> findComment(String name, String news);

    List<Comment> findByNews(String news);

    List<Comment> orderByNumber();

    void addComment(String name, String news, Integer number, String comment);

    void updateComment(String name, String news, Integer number, String comment);

    List<Comment> findByName(String name);

    Integer getCol(String name);

    void addNumber(String name, String news);
}
