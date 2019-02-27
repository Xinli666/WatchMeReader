package com.userexample.userexample.bean;

import javax.persistence.*;

@IdClass(WordPK.class)
@Entity
@Table(name = "news_keywords")
public class Word {
    @Id
    @Column(name = "newsID")
    private Integer doc;    //文档编号
    @Id
    @Column(name = "keywords")
    private String word;    //单词
    @Column(name = "number",nullable = false)
    private Integer num;    //单词出现次数

    public Word(){ }

    public Word(Integer doc, String word, Integer num) {
        this.doc = doc;
        this.word = word;
        this.num = num;
    }

    public Integer getDoc() {
        return doc;
    }

    public void setDoc(Integer doc) {
        this.doc = doc;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
