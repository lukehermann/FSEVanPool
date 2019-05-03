package com.springboot.service;

import com.springboot.model.Role;
import com.springboot.model.User;
import com.springboot.repository.RoleRepository;
import com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRespository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setConfirmPassword(encodedPassword);

        user.setAnswerOne(bCryptPasswordEncoder.encode(user.getAnswerOne()));
        user.setAnswerTwo(bCryptPasswordEncoder.encode(user.getAnswerTwo()));
        user.setAnswerThree(bCryptPasswordEncoder.encode(user.getAnswerThree()));
        user.setActive(1);
        Role userRole = roleRespository.findByRole(user.getRole());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        user.setRoleID(userRole.getId());
        userRepository.save(user);
    }

    @Override
    public void updatePassword(String password, int userId)
    {
        userRepository.updatePassword(password, userId);
    }

    @Override
    public void updateRoutes(String routes, int id)
    {
        userRepository.updateRoutes(routes, id);
    }

    @Override
    public String getRoutes(int id)
    {
        return userRepository.getRoutes(id);
    }

    @Override
    public String getHistory(int id){
        return userRepository.getHistory(id);
    }

    @Override
    public void updateHistory(String history, int userId){
        userRepository.updateHistory(history, userId);
    }

    @Override
    public void updateSunday(int userId, boolean change){
        userRepository.updateSunday(userId, change);
    }

    @Override
    public void updateMonday(int userId, boolean change){
        userRepository.updateMonday(userId, change);
    }

    @Override
    public void updateTuesday(int userId, boolean change){
        userRepository.updateTuesday(userId, change);
    }

    @Override
    public void updateWednesday(int userId, boolean change){
        userRepository.updateWednesday(userId, change);
    }

    @Override
    public void updateThursday(int userId, boolean change){
        userRepository.updateThursday(userId, change);
    }

    @Override
    public void updateFriday(int userId, boolean change){
        userRepository.updateFriday(userId, change);
    }

    @Override
    public void updateSaturday(int userId, boolean change){
        userRepository.updateSaturday(userId, change);
    }
}