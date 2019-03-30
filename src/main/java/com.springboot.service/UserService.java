package com.springboot.service;

import com.springboot.model.User;

public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);

    void updatePassword(String password, int userId);

}