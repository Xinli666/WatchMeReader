package com.userexample.userexample.bean;

import java.io.Serializable;
import java.util.Objects;

public class CClass implements Serializable {
    private String name;
    private String news;

    public CClass(){}

    public CClass(String userID, String newsID){
        this.name = userID;
        this.news = newsID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CClass)) return false;
        CClass cClass = (CClass) o;
        return Objects.equals(getName(), cClass.getName()) &&
                Objects.equals(getNews(), cClass.getNews());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getNews());
    }
}
