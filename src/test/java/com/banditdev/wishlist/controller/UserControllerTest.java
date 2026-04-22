package com.banditdev.wishlist.controller;

import com.banditdev.wishlist.model.User;
import com.banditdev.wishlist.repository.UserRepository;
import com.banditdev.wishlist.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    //Redirects when user is already logged in
    @Test
    void login() throws Exception {
        User mockUser = new User(1, "test@mail.com", "Test", "password");

        mockMvc.perform(get("/user/login")
                        .sessionAttr("user", mockUser))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlist"));
    }


    //Logs out user and verifies that the user is redirected to the index page
    @Test
    void logout() throws Exception {
        User mockUser = new User(1, "test@mail.com", "Test", "password");

        mockMvc.perform(get("/user/logout")
                        .sessionAttr("user", mockUser))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(request ->
                        assertNull(request.getRequest().getSession(false))
                );
    }

    //Saves a new user
    @Test
    void saveNewUser() throws Exception {
        User savedUser = new User(1, "new@mail.com", "New", "password");
        when(userService.addUser(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/user/save")
                        .param("userName", "New User")
                        .param("userEmail", "new@mail.com")
                        .param("userPassword", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlist"));

        verify(userService).addUser(any(User.class));
    }
}