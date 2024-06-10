package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.UserModel;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public List<UserEntity> findAllUsers();

    public UserEntity findUser(Integer id);

    public UserEntity addNewUser(UserModel user);

    public List<UserEntity> filterUsers(String name, String email);
}
