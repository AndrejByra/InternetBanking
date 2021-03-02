package com.core.internetbanking.service;

import com.core.internetbanking.dto.UserDto;
import com.core.internetbanking.model.User;
import com.core.internetbanking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Integer createUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmailAddress(userDto.getEmailAddress());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return user.getAccountId();

    }

    @Override
    public Integer updateUser(Integer accountId, UserDto userDto) {
        User userToUpdate = userRepository.getOne(accountId);
        userToUpdate.setFirstName(userDto.getFirstName());
        userToUpdate.setLastName(userDto.getLastName());
        userToUpdate.setEmailAddress(userDto.getEmailAddress());
        userToUpdate.setDateOfBirth(userDto.getDateOfBirth());
        userToUpdate.setPassword(userDto.getPassword());
        userRepository.save(userToUpdate);
        return userToUpdate.getAccountId();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(Integer accountId) {
        return userRepository.findById(accountId);
    }

    @Override
    public void deleteById(Integer accountId) {
        userRepository.deleteById(accountId);
    }
}
