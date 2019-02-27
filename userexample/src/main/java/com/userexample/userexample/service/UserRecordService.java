package com.userexample.userexample.service;

import com.userexample.userexample.bean.News;
import com.userexample.userexample.bean.UserRecord;

import java.util.List;

public interface UserRecordService {
    List<UserRecord> findByName(String name);

    void addUserRecord(String name, Integer newsID, String news, String time);

    List<UserRecord> findByID(String name, String news);

    Integer getColumn(String name);

    List<Integer> getRecord(String name);
}
