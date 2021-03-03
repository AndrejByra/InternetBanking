package com.core.internetbanking.service;

import com.core.internetbanking.dto.UserDto;
import com.core.internetbanking.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Integer createUser(UserDto userDto);

    Integer updateUser(Integer accountId, UserDto userDto);

    Optional<User> getUser(Integer accountId);

    List<User> getUsers();

    void deleteById(Integer accountId);
}