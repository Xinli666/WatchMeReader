package com.userexample.userexample.service;

import com.userexample.userexample.bean.User;
import com.userexample.userexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @Override
    public long getColNum(){
        return userRepository.count();
    }

    @Override
    public void addUser(int userID, String name, String phone, String password){
        userRepository.addUser(userID, name, phone, password);
    }

    @Override
    public void updateUser(int userID, String name, String phone, String password){
        userRepository.updateUser(userID, name, phone, password);
    }

    @Override
    public Optional<User> findByID(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(User user){
        userRepository.delete(user);
    }

    @Override
    public List<User> findByName(String name){
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findByPhone(String phone){
        return userRepository.findByPhone(phone);
    }

    @Override
    public List<User> queryByID(Integer id){
        return userRepository.queryByID(id);
    }

    @Override
    public List<User> page(int p1, int p2){
        return userRepository.page(p1, p2);
    }

    @Override
    public void updateDelete(int id){
        userRepository.updateDelete(id);
    }

    @Override
    public Optional<User> login(String name, String password){
        return userRepository.login(name, password);
    }
}
