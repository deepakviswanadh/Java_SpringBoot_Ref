package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.DTO.UserDTO;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name="CRUD Rest APIs for User Resource",
        description = "Ops to CRUD a user"
)
@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    @Operation(summary = "Fetch all users",
            description = "Fetch all the users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all users"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Unexpected error occurred")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<UserEntity>> fetchAllUsers() {
        logger.info("/users/findAll hit started");
        List<UserEntity> users = userService.findAllUsers();
        logger.info("/users/findAll hit complete");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Find a user",
            description = "Path/id based finding a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the user"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid parameters"),
            @ApiResponse(responseCode = "404", description = "Not Found - No users match the given criteria"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Unexpected error occurred")
    })
    @GetMapping("/findUser/{id}")
    public ResponseEntity<UserDTO> fetchUser(@PathVariable(name="id")Integer id) {
        logger.info("/users/findUser/{} hit started for the user id: {}",id,id);
        UserDTO user = userService.findUserById(id);
        logger.info("/users/findUser hit complete for the user id: {}",id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Add new user",
            description = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a new user"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid parameters"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Unexpected error occurred")
    })
    @PostMapping("/addNewUser")
    public ResponseEntity<UserDTO> addNewUser(@Valid @RequestBody UserDTO user) {
        logger.info("/users/addNewUser hit started with request body {}",user);
        UserDTO newUser = userService.addNewUser(user);
        logger.info("/users/addNewUser complete with new User {}",newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Filter users based on email and name",
    description = "Query Params based filtering of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the filtered users"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid parameters"),
            @ApiResponse(responseCode = "404", description = "Not Found - No users match the given criteria"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Unexpected error occurred")
    })
    @GetMapping("/findUser")
    public ResponseEntity<List<UserEntity>> filterUsers(@RequestParam(name="name")String name,
                                              @RequestParam(name="email")String email) {
        logger.info("/users/findUser hit started for: name: {} email :{}",name,email);
        List<UserEntity>filteredUsers= userService.filterUsers(name,email);
        logger.info("/users/findUser hit complete for: name: {} email :{}",name,email);
        return new ResponseEntity<>(filteredUsers,HttpStatus.OK);
    }

}
