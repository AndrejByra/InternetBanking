package com.core.internetbanking.service;

import com.core.internetbanking.controller.UserController;
import com.core.internetbanking.dto.UserDto;
import com.core.internetbanking.model.User;
import com.core.internetbanking.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class UserServiceImplTest {

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @InjectMocks
    private UserController userController = new UserController();

    @Mock
    UserRepository userRepository;

    @Test
    void testCreateUserSuccess() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("test1");
        userDto.setLastName("test2");
        userDto.setDateOfBirth("test3");
        userDto.setEmailAddress("test4");
        userDto.setPassword("test5");

        User user = new User();
        user.setFirstName("test1");
        user.setLastName("test2");
        user.setDateOfBirth("test3");
        user.setEmailAddress("test4");
        user.setPassword("test5");

        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(user);

        User userResult = userService.createUser(userDto);
        assertEquals("test1", userResult.getFirstName());
        assertEquals("test2", userResult.getLastName());
        assertEquals("test3", userResult.getDateOfBirth());
        assertEquals("test4", userResult.getEmailAddress());
        assertEquals("test5", userResult.getPassword());
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setAccountId(1);
        Optional<User> userOptional = Optional.of(user);

        Mockito.when(userRepository.findById(Mockito.any()))
                .thenReturn(userOptional);

        Optional<User> userResult = userService.getUser(user.getAccountId());
        assertEquals(1, userResult.get().getAccountId());
    }


    @Test
    void testGetAllUsers() {
        User user = new User();
        user.setAccountId(1);

        User user2 = new User();
        user2.setAccountId(2);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);

        Mockito.when(userRepository.findAll())
                .thenReturn(userList);

        Iterable<User> userResults = userService.getUsers();
        ArrayList<User> userArrayList = new ArrayList<>();

        userResults.forEach(userArrayList::add);
        assertEquals(1, userArrayList.get(0).getAccountId());
        assertEquals(2, userArrayList.get(1).getAccountId());

    }

    @Test
    void testUpdateUser() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("test1");
        userDto.setLastName("test2");
        userDto.setDateOfBirth("test3");
        userDto.setEmailAddress("test4");
        userDto.setPassword("test5");

        User user = new User();
        user.setFirstName("test1");
        user.setLastName("test2");
        user.setDateOfBirth("test3");
        user.setEmailAddress("test4");
        user.setPassword("test5");

        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(user);

        Mockito.when(userRepository.getOne(1))
                .thenReturn(new User());

        User userResult = userService.updateUser(1,userDto);
        assertEquals("test1", userResult.getFirstName());
        assertEquals("test2", userResult.getLastName());
        assertEquals("test3", userResult.getDateOfBirth());
        assertEquals("test4", userResult.getEmailAddress());
        assertEquals("test5", userResult.getPassword());
    }

    @Test
    void testDeleteAccountById() {
        User user = new User();
        user.setAccountId(1);

        User user2 = new User();
        user2.setAccountId(2);

        userRepository.deleteById(user.getAccountId());
        userRepository.deleteById(user2.getAccountId());

        Mockito.verify(userRepository, Mockito.times(2)).
                deleteById(Mockito.anyInt());
    }
}
