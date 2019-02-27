package com.userexample.userexample.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Feedback {
    @Id
    private String email;
    private String comment;

    public Feedback(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
