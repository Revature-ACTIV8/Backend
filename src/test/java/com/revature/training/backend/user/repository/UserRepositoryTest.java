package com.revature.training.backend.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.revature.training.backend.common.exception.UserNotFoundException;
import com.revature.training.backend.user.model.User;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindAllUsers() {
        User user1 = new User("Alpha", "alpha@mail.com", "password1");
        User user2 = new User("Bravo", "bravo@mail.com", "password2");
        User user3 = new User("Charlie", "charlie@mail.com", "password3");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        List<User> users = userRepository.findAll();

        assertNotNull(users);
        assertEquals(3, users.size());
    }

    @Test
    public void testSaveAndFindUser() {
        User user = new User("Delta", "delta@mail.com", "pass1234");
        userRepository.save(user);

        User foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertNotNull(foundUser);
        assertEquals(foundUser, user);
    }

    @Test
    public void testExistsByEmail_EmailExists() {
        User user = new User("Echo", "echo@mail.com", "pass1234");
        userRepository.save(user);

        boolean userExists = userRepository.existsByEmail("echo@mail.com");

        assertEquals(userExists, true);
    }

    @Test
    public void testExistsByEmail_EmailDoesNotExists() {
        boolean userExists = userRepository.existsByEmail("hotel@mail.com");

        assertEquals(userExists, false);
    }

    @Test
    public void testDeleteUser() {
        User user = new User("Foxtrot", "foxtrot@mail.com", "pass1234");
        userRepository.save(user);
        
        userRepository.delete(user);

        assertTrue(userRepository.findById(user.getId()).isEmpty());
        assertThrows(UserNotFoundException.class, () -> userRepository.findById(user.getId()));
    }
}
