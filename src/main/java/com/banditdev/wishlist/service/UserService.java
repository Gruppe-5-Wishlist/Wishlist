package com.banditdev.wishlist.service;

import com.banditdev.wishlist.model.User;
import com.banditdev.wishlist.repository.UserRepository;
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
}
//TODO - Make a login() validation method with if statement