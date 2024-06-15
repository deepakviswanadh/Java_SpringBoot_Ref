package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.DTO.UserDTO;

import java.util.List;

public interface UserService {
    public List<UserEntity> findAllUsers();

    public UserDTO findUserById(Integer id);

    public UserDTO addNewUser(UserDTO user);

    public List<UserEntity> filterUsers(String name, String email);
}
