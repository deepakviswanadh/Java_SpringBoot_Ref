package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.DTO.UserDTO;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    @GetMapping("/findAll")
    public ResponseEntity<List<UserEntity>> fetchAllUsers() {
        logger.info("/users/findAll hit started");
        List<UserEntity> users = userService.findAllUsers();
        logger.info("/users/findAll hit complete");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/findUser/{id}")
    public ResponseEntity<UserDTO> fetchUser(@PathVariable(name="id")Integer id) {
        logger.info("/users/findUser/{} hit started for the user id: {}",id,id);
        UserDTO user = userService.findUserById(id);
        logger.info("/users/findUser hit complete for the user id: {}",id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<UserDTO> addNewUser(@Valid @RequestBody UserDTO user) {
        logger.info("/users/addNewUser hit started with request body {}",user);
        UserDTO newUser = userService.addNewUser(user);
        logger.info("/users/addNewUser complete with new User {}",newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

//    @ApiOperation(value = "Filter users by name and email", notes = "Provide name and email to filter users")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Successfully retrieved the filtered users"),
//            @ApiResp nse(code = 400, message = "Bad Request - Invalid parameters"),
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
