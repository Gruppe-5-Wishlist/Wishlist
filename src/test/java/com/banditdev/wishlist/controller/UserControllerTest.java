package com.banditdev.wishlist.controller;

import com.banditdev.wishlist.repository.UserRepository;
import com.banditdev.wishlist.repository.WishlistRepository;
import com.banditdev.wishlist.service.UserService;
import com.banditdev.wishlist.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserControllerTest.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    void login() {
    }

    @Test
    void testLogin() {
    }

    @Test
    void logout() {
    }

    @Test
    void createNewUser() {
    }

    @Test
    void saveNewUser() {
    }

    @Test
    void showProfilePage() {
    }

    @Test
    void editProfile() {
    }

    @Test
    void saveProfile() {
    }

    @Test
    void deleteCurrentUser() {
    }
}