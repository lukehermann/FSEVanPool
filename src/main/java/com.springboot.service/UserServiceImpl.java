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
    private RoleRepository routeRepository;

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
    public void updatePassword(String password, int userId) {
        userRepository.updatePassword(password, userId);
    }

}