package com.banditdev.wishlist.service;

import com.banditdev.wishlist.model.User;
import com.banditdev.wishlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    public void deleteUser(int userID) {
        userRepository.deleteUser(userID);
    }

    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public boolean validateUser(String userEmail, String userPassword) {

            User user = userRepository.findUserByEmail(userEmail);
            if (user != null && user.getUserPassword().equals(userPassword)) {
                return true;
            } else {
                return false;
            }
    }
}
//TODO - Make a login() validation method with if statement - needs review