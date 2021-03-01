package com.core.internetbanking.service;

import com.core.internetbanking.dto.UserDto;
import com.core.internetbanking.model.User;

import java.util.Optional;

public interface UserService {
    User createUser(UserDto userDto);

    User updateUser(Integer accountId, UserDto userDto);

    Optional<User> getUser(Integer accountId);

    Iterable<User> getUsers();

    void deleteById(Integer accountId);
}
