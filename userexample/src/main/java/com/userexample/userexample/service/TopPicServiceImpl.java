package com.userexample.userexample.service;

import com.userexample.userexample.bean.TopPic;
import com.userexample.userexample.repository.TopPicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopPicServiceImpl implements TopPicService{
    @Autowired
    private TopPicRepository topPicRepository;

    @Override
    public List<TopPic> getAll(){
        return topPicRepository.findAll();
    }

}
