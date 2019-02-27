package com.userexample.userexample.service;

import com.userexample.userexample.bean.News;
import com.userexample.userexample.bean.UserRecord;
import com.userexample.userexample.repository.UserRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRecordServiceImpl implements UserRecordService{
    @Autowired
    private UserRecordRepository userRecordRepository;

    @Override
    public List<UserRecord> findByName(String name){
        return userRecordRepository.findByName(name);
    }

    @Override
    public void addUserRecord(String name, Integer newsID, String news, String time){
        userRecordRepository.addUserRecord(name, newsID, news, time);
    }

    @Override
    public List<UserRecord> findByID(String name, String news){
        return userRecordRepository.findByID(name, news);
    }

    @Override
    public Integer getColumn(String name){
        return userRecordRepository.getColumn(name);
    }

    @Override
    public List<Integer> getRecord(String name){
        return userRecordRepository.getRecord(name);
    }
}
