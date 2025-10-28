package com.revature.training.backend.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.training.backend.common.exception.EmailAlreadyExistsException;
import com.revature.training.backend.user.model.User;
import com.revature.training.backend.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void testCreateUser_UserCreated() {
        User user = new User("alpha", "alpha@mail.com", "password1");
        
        when(userRepository.save(user)).thenReturn(user);
        
        User savedUser = userService.createUser(user);
        
        assertEquals(user, savedUser);
        verify(userRepository).save(user);
    }

    @Test
    public void testCreateUser_EmailAleadyExists() {
        User user1 = new User("alpha", "alpha@mail.com", "password1");

        when(userRepository.save(user1)).thenThrow(new EmailAlreadyExistsException(
            "Email " + user1.getEmail() + " already exists!"
        ));
        
        assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(user1));
        verify(userRepository).save(user1);
    }

    @Test
    public void testFindUserById_UserFound() {
        Long userId = 1L;
        User user = new User("bravo", "bravo@mail.com", "password2");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> userFound = userRepository.findById(userId);

        assertNotNull(userFound);
        assertEquals(user, userFound.get());
        verify(userRepository).findById(userId);
    }

    @Test
    public void testFindUserById_UserNotFound() {
        Long userId = 2L;
        User user = new User("charlie", "charlie@mail.com", "password3");
        userRepository.save(user);

        Optional<User> userNotFound = userRepository.findById(userId);

        assertEquals(userNotFound, Optional.empty());
        verify(userRepository).findById(userId);
    }

    @Test
    public void testFindAllUsers() {
        User user1 = new User("alpha", "alpha@mail.com", "password1");
        User user2 = new User("bravo", "bravo@mail.com", "password2");
        User user3 = new User("charlie", "charlie@mail.com", "password3");
        List<User> users = List.of(user1, user2, user3);

        when(userRepository.findAll()).thenReturn(users);

        List<User> usersFound = userService.getAllUsers();

        assertEquals(usersFound, users);
        assertEquals(3, users.size());
        verify(userRepository).findAll();
    }

    @Test
    public void testUpdateUser_UserUpdatedPassword() {
        Long userId = 1L;
        User user = new User("alpha", "alpha@mail.com", "password1");
        User userUpdate = new User("alpha", "alpha@mail.com", "1234pass");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(userUpdate)).thenReturn(userUpdate);

        User updatedUser = userService.updateUser(userId, userUpdate);

        assertEquals(updatedUser.getUserName(), "alpha");
        assertEquals(updatedUser.getEmail(), "alpha@mail.com");
        assertNotEquals(updatedUser.getPassword(), "password1");
        assertEquals(updatedUser.getPassword(), "1234pass");
        verify(userRepository).findById(userId);
        verify(userRepository).save(userUpdate);
    }

    @Test
    public void testDeleteUserById_UserDeleted() {
        Long userId = 1L;
        User user = new User("alpha", "alpha@mail.com","password1");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUser(userId);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(user);
    }
}
