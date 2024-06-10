package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.ResponseModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @GetMapping("/findAll")
    public ResponseEntity<List<UserEntity>> fetchAllUsers() {
        logger.info("/users/findAll hit started");
        List<UserEntity> users = userService.findAllUsers();
        logger.info("/users/findAll hit complete");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/findUser/{id}")
    public ResponseEntity<Object> fetchUser(@PathVariable(name="id")Integer id) {
        logger.info("/users/findUser/{} hit started for the user id: {}",id,id);
        UserEntity user = userService.findUser(id);
        if(Objects.isNull(user)){
            logger.info("User with id {} not found", id);
            logger.info("/users/findUser/{} hit complete for the user id: {}",id,id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested resource is not found");
        }
        logger.info("/users/findUser hit complete for the user id: {}",id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<UserEntity> addNewUser(@RequestBody UserModel user) {
        logger.info("/users/addNewUser hit started with request body {}",user);
        UserEntity newUser = userService.addNewUser(user);
        logger.info("/users/addNewUser complete with new User {}",newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

//    @ApiOperation(value = "Filter users by name and email", notes = "Provide name and email to filter users")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Successfully retrieved the filtered users"),
//            @ApiResponse(code = 400, message = "Bad Request - Invalid parameters"),
//            @ApiResponse(code = 404, message = "Not Found - No users match the given criteria"),
//            @ApiResponse(code = 500, message = "Internal Server Error - Unexpected error occurred")
//    })
    @GetMapping("/findUser")
    public ResponseEntity<List<UserEntity>> filterUsers(@RequestParam(name="name")String name,
                                              @RequestParam(name="email")String email) {
        logger.info("/users/findUser hit started for: name: {} email :{}",name,email);
        List<UserEntity>filteredUsers= userService.filterUsers(name,email);
        logger.info("/users/findUser hit complete for: name: {} email :{}",name,email);
        return new ResponseEntity<>(filteredUsers,HttpStatus.OK);
    }

}
