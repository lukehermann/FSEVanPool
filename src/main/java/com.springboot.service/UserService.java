package com.springboot.service;

import com.springboot.model.User;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    void updatePassword(String password, int userId);

    void updateRoutes(String routes, int id);

    String getRoutes(int id);

    String getHistory(int id);

    void updateHistory(String history, int userId);

    void updateSunday(int userId, boolean change);

    void updateMonday(int userId, boolean change);

    void updateTuesday(int userId, boolean change);

    void updateWednesday(int userId, boolean change);

    void updateThursday(int userId, boolean change);

    void updateFriday(int userId, boolean change);

    void updateSaturday(int userId, boolean change);



}