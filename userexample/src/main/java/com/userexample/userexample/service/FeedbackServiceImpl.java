package com.userexample.userexample.service;

import com.userexample.userexample.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public void addFeedback(String email, String comment){
        feedbackRepository.addFeedback(email, comment);
    }
}
