package com.example.demo.mapper.mapperimpl;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapperImpl implements UserMapper {

    private ModelMapper modelMapper;

    @Override
    public UserEntity toEntity(UserDTO userModel) {
        return modelMapper.map(userModel, UserEntity.class);
    }

    @Override
    public UserDTO toModel(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }
}
