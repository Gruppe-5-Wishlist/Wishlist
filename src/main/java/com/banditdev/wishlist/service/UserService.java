package com.banditdev.wishlist.service;

import com.banditdev.wishlist.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
//TODO - Make a login() validation method with if statement