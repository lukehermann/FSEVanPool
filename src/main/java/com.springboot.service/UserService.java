package com.springboot.service;

import com.springboot.model.User;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    void updatePassword(String password, int userId);

    void updateRoutes(String routes, int id);

    String getRoutes(int id);

    String getHistory(int id);

    void updateHistory(String history , int userId);
}