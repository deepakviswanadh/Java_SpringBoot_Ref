package com.example.demo.service.serviceimpl;

import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.GeneralServiceException;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;


    @Override
    public List<UserEntity> findAllUsers() {
        try{

            logger.info("findAllUsers() service hit started");
            List<UserEntity> users=userRepository.findAll();
            logger.info("findAllUsers() service hit successful");
            return users;
        }catch(Exception e){
            logger.error("Error in findAllUsers() service: {}", e.getMessage());
            throw new GeneralServiceException("UserServiceImpl Service Layer Exception", e);
        }
    }

    @Override
    public UserEntity findUser(Integer id) {
        try {
            logger.info("findUser() Service hit started for user id: {}", id);
            UserEntity user = userRepository.findUserByName(id);
            logger.info("findUser Service hit successful, Found: {}", user);
            return user;
        } catch (DataAccessException e) {
            logger.error("Error in findUser() service: {}", e.getMessage());
            throw new GeneralServiceException("UserServiceImpl Service Layer Exception", e);
        }
    }

    @Override
    public UserEntity addNewUser(UserModel user) {
       try{
           logger.info("addNewUser() Service hit started for user: {}", user);
           UserEntity newUser = UserEntity.builder()
                   .name(user.name)
                   .email(user.email)
                   .address(user.address)
                   .phonenumber(user.phone_number)
                   .build();
           userRepository.save(newUser);
           logger.info("addNewUser Service hit completed for user: {}", newUser);
           return newUser;
       }catch(Exception e){
           logger.error("Error in addNewUser() service: {}", e.getMessage());
           throw new GeneralServiceException("UserServiceImpl Service Layer Exception", e);
       }
    }

    @Override
    public List<UserEntity> filterUsers(String name, String email) {
        try{
            logger.info("filterUsers() Service hit started for name: {} and email: {}", name, email);
            List<UserEntity>users = userRepository.findUsersByFilters(name,email);
            logger.info("filterUsers() Service hit completed with users: {}",users);
            return users;
        }catch(Exception e){
            logger.error("Error in filterUsers() service: {}", e.getMessage());
            throw new GeneralServiceException("UserServiceImpl Service Layer Exception", e);
        }
    }
}
