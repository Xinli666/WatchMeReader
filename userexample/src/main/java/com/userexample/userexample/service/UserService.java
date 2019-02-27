package com.userexample.userexample.service;

import com.userexample.userexample.bean.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();

    long getColNum();

    void addUser(int userID, String name, String phone, String password);

    void updateUser(int userID, String name, String phone, String password);

    Optional<User> findByID(Integer id);

    void deleteUser(User user);

    List<User> findByName(String name);

    List<User> findByPhone(String phone);

    List<User> queryByID(Integer id);

    List<User> page(int p1, int p2);

    void updateDelete(int id);

    Optional<User> login(String name, String password);
}
