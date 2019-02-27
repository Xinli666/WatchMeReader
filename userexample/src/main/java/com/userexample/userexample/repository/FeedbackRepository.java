package com.userexample.userexample.repository;

import com.userexample.userexample.bean.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface FeedbackRepository extends JpaRepository<Feedback, String> {
    @Modifying
    @Transactional
    @Query(value = "insert into feedback(email, comment) value(?1, ?2)", nativeQuery = true)
    void addFeedback(String email, String comment);
}
