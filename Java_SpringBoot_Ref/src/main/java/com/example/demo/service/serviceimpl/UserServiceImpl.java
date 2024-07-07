package com.example.demo.service.serviceimpl;

import com.example.demo.DTO.WebClientDTO;
import com.example.demo.apis.openfeign.APIClient;
import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.Types.FieldAlreadyExistsException;
import com.example.demo.exceptions.Types.GeneralServiceException;
import com.example.demo.DTO.UserDTO;
import com.example.demo.exceptions.Types.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final APIClient apiClient;

    @Override
    public List<UserEntity> findAllUsers() {
        try {

            logger.info("findAllUsers() service hit started");
            List<UserEntity> users = userRepository.findAll();
            logger.info("findAllUsers() service hit successful");
            return users;
        } catch (Exception e) {
            logger.error("Error in findAllUsers() service: {}", e.getMessage());
            throw new GeneralServiceException("UserServiceImpl Service Layer Exception");
        }
    }

    @Override
    public UserDTO findUserById(Integer id) {
        try {
            logger.info("findUser() Service hit started for user id: {}", id);
            UserDTO user = userMapper.toModel(userRepository.findUserById(id).orElseThrow(
                    () -> new ResourceNotFoundException("User", "Id", id)
            ));
            WebClientDTO response=apiClient.getDepartment();
            logger.info("Response returned from OpenFeign {}", response);
            return user;
        } catch (DataAccessException e) {
            logger.error("Error in findUser() service: {}", e.getMessage());
            throw new GeneralServiceException("UserServiceImpl Service Layer Exception");
        }
    }

    @Override
    public UserDTO addNewUser(UserDTO user) {
        try {
            logger.info("addNewUser() Service hit started for user: {}", user);
            Optional<UserEntity> exisitingUser = userRepository.findByEmail(user.getEmail());
            if (exisitingUser.isPresent()) {
                throw new FieldAlreadyExistsException("Email", "email", user.getEmail());
            }
//         UserEntity newUser = UserEntity
//                   .builder()
//                   .name(user.name)
//                   .email(user.email)
//                   .address(user.address)
//                   .phonenumber(user.phone_number)
//                   .build();
            UserEntity newUser = userMapper.toEntity(user);
            userRepository.save(newUser);
            logger.info("addNewUser Service hit completed for user: {}", newUser);
            return userMapper.toModel(newUser);
        } catch (Exception e) {
            if (e instanceof FieldAlreadyExistsException) {
                throw (FieldAlreadyExistsException) e;
            }
            logger.error("Error in addNewUser() service: {}", e.getMessage());
            throw new GeneralServiceException("UserServiceImpl Service Layer Exception");
        }
    }

    @Override
    public List<UserEntity> filterUsers(String name, String email) {
        try {
            logger.info("filterUsers() Service hit started for name: {} and email: {}", name, email);
            Optional<List<UserEntity>> usersOptional = userRepository.findUsersByFilters(name, email);
            //To:Do: (updated) -> Move this to criteria builder & predicate
            // and handle filtering there with varying params

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
            throw new GeneralServiceException("UserServiceImpl Service Layer Exception");
        }
    }
}
