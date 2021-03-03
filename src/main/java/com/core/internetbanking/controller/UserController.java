package com.core.internetbanking.controller;

import com.core.internetbanking.dto.UserDto;
import com.core.internetbanking.model.User;
import com.core.internetbanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Integer createNewUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{accountId}")
    public Optional<User> getUserById(@PathVariable("accountId") Integer accountId) {
        return userService.getUser(accountId);
    }

    @PutMapping
    public Integer updateUser(@RequestParam(value = "accountId") Integer accountId, @RequestBody UserDto userDto) {
        return userService.updateUser(accountId, userDto);
    }

    @DeleteMapping(path = "/{accountId}")
    public void deleteByAccountId(@PathVariable Integer accountId) {
        userService.deleteById(accountId);
    }
}