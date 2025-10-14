package com.revature.training.backend.service;

import org.springframework.stereotype.Service;

import com.revature.training.backend.exception.UserNotFoundException;
import com.revature.training.backend.model.User;
import com.revature.training.backend.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException("User id " + id + " not found"));
    }

    public User getUserByEmail(String email) throws UserNotFoundException
    {
        if (userRepository.existsByEmail(email))
        {
            return userRepository.findByEmail(email);
        }
        
        throw new UserNotFoundException("User email " + email + " does not exists!");
    }

    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);

        existingUser.setUserName(user.getUserName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }
}
