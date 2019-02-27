package com.userexample.userexample.bean;

import java.io.Serializable;
import java.util.Objects;

public class WordPK implements Serializable {
    private Integer doc;
    private String word;

    public WordPK(){
    }

    private Integer getDoc() {
        return doc;
    }

    public void setDoc(Integer doc) {
        this.doc = doc;
    }

    private String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordPK)) return false;
        WordPK wordPK = (WordPK) o;
        return Objects.equals(getDoc(), wordPK.getDoc()) &&
                Objects.equals(getWord(), wordPK.getWord());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getDoc(), getWord());
    }
}
