package com.antoniovinter.crud.controller;

import com.antoniovinter.crud.dto.UserDto;
import com.antoniovinter.crud.model.User;
import com.antoniovinter.crud.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserDto> getAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable int id){
        return userService.findById(id);
    }

    @GetMapping("/name")
    public UserDto getUserByName(@RequestParam(required = false) String value){
        return userService.findByName(value);
    }

    @PostMapping("")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

}
