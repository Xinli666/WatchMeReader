package com.userexample.userexample.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "comment")
@IdClass(CClass.class)
public class Comment {
    @Id
    private String name;
    @Id
    private String news;
    private Integer number;
    private String comment;

    public String getName(){
        return this.name;
    }

    public String getNews(){
        return this.news;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNews(String newsID) {
        this.news = newsID;
    }

    @Override
    public String toString(){
        return "Comment{" +
                "name=" + name +
                ", news=" + news +
                ", number=" + number +
                ", comment=" + comment + '\'' +
                '}';
    }
}
