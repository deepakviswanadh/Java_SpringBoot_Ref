package com.example.demo.service.serviceimpl;

import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.GeneralServiceException;
import com.example.demo.config.DTO.UserDTO;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserRepository userRepository;

    private UserMapper userMapper;


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
    public UserDTO findUserById(Integer id) {
        try {
            logger.info("findUser() Service hit started for user id: {}", id);
            UserDTO user = userMapper.toModel(userRepository.findUserById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("User","Id",id)
            ));
            logger.info("findUser Service hit successful, Found: {}", user);
            return user;
        } catch (DataAccessException e) {
            logger.error("Error in findUser() service: {}", e.getMessage());
            throw new GeneralServiceException("UserServiceImpl Service Layer Exception", e);
        }
    }

    @Override
    public UserDTO addNewUser(UserDTO user) {
       try{
           logger.info("addNewUser() Service hit started for user: {}", user);
//           UserEntity newUser = UserEntity.builder()
//                   .name(user.name)
//                   .email(user.email)
//                   .address(user.address)
//                   .phonenumber(user.phone_number)
//                   .build();
           UserEntity newUser= userMapper.toEntity(user);
           userRepository.save(newUser);
           logger.info("addNewUser Service hit completed for user: {}", newUser);
           return userMapper.toModel(newUser);
       }catch(Exception e){
           logger.error("Error in addNewUser() service: {}", e.getMessage());
           throw new GeneralServiceException("UserServiceImpl Service Layer Exception", e);
       }
    }

    @Override
    public List<UserEntity> filterUsers(String name, String email) {
        try {
            logger.info("filterUsers() Service hit started for name: {} and email: {}", name, email);
            Optional<List<UserEntity>> usersOptional = userRepository.findUsersByFilters(name, email);
            //To:Do: (updated) -> Move this to predicate and handle filtering there with varying params

            //To:Do -> remove this throw as this will never throw an exception
            //validate the query params by checking if they really exist in db
            //if they do and no results are found after filter, send 200 and empty list
            //if one of the query params is invalid (not found in db), throw 404 and return invalid params
            List<UserEntity> users = usersOptional.orElseThrow(() ->
                    new ResourceNotFoundException("Users", "name and email", name + " " + email)
            );
            logger.info("filterUsers() Service hit completed with users: {}", users);
            return users;
        } catch (Exception e) {
            logger.error("Error in filterUsers() service: {}", e.getMessage());
            throw new GeneralServiceException("UserServiceImpl Service Layer Exception", e);
        }
    }
}
