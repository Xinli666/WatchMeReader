package com.userexample.userexample.service;

import com.userexample.userexample.bean.Comment;
import com.userexample.userexample.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getAll(){
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findComment(String name, String news){
        return commentRepository.findComment(name, news);
    }

    @Override
    public List<Comment> findByNews(String news){
        return commentRepository.findByNews(news);
    }

    @Override
    public List<Comment> orderByNumber(){
        return commentRepository.orderByNumber();
    }

    @Override
    public void addComment(String name, String news, Integer number, String comment){
        commentRepository.addComment(name, news, number, comment);
    }

    @Override
    public void updateComment(String name, String news, Integer number, String comment){
        commentRepository.updateComment(name, news, number, comment);
    }

    @Override
    public List<Comment> findByName(String name){
        return commentRepository.findByName(name);
    }

    @Override
    public Integer getCol(String name){
        return commentRepository.getCol(name);
    }

    @Override
    public void addNumber(String name, String news){
        commentRepository.addNumber(name, news);
    }
}
