package com.revature.training.backend.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.training.backend.common.exception.EmailAlreadyExistsException;
import com.revature.training.backend.common.exception.InvalidPasswordException;
import com.revature.training.backend.common.exception.PasswordMismatchException;
import com.revature.training.backend.common.exception.UserNotFoundException;
import com.revature.training.backend.user.DTO.PasswordUpdateRequest;
import com.revature.training.backend.user.model.User;
import com.revature.training.backend.user.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email " + user.getEmail() + " already exists!");
        }
        else {
            return userRepository.save(user);
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException("User id " + id + " not found"));
    }

    public User getUserByEmail(String email) {
        if (userRepository.existsByEmail(email))
        {
            return userRepository.findByEmail(email);
        }
        
        throw new UserNotFoundException("User email " + email + " does not exists!");
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    public void updatePassword(PasswordUpdateRequest request) {
        User currentUser = request.getUser();
        User existingUser = getUserByEmail(currentUser.getEmail());

        if (!request.getCurrentPassword().equals(existingUser.getPassword())) {
            throw new InvalidPasswordException("Current password is incorrect!");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("New passwords do not match!");
        }

        existingUser.setPassword(request.getNewPassword());

        userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }
}
