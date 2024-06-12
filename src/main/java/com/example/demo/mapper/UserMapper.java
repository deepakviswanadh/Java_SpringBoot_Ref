package com.example.demo.mapper;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.UserEntity;


public interface UserMapper {
    UserEntity toEntity(UserDTO userModel);
    UserDTO toModel(UserEntity userEntity);
}

