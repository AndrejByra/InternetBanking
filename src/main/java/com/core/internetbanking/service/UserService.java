package com.core.internetbanking.service;

import com.core.internetbanking.dto.UserDto;
import com.core.internetbanking.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * method to save user in database
     * return id of user
     */
    Integer createUser(UserDto userDto);

    /**
     * method to update user informations in database
     * return id of user
     */
    Integer updateUser(Integer accountId, UserDto userDto);

    /**
     * method to get specific user from database
     * return user
     */
    Optional<User> getUser(Integer accountId);

    /**
     * method to get all users form database
     * return list of users
     */
    List<User> getUsers();

    /**
     * method to delete user
     */
    void deleteById(Integer accountId);
}