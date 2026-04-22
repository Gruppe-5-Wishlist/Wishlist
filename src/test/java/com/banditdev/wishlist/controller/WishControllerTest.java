package com.banditdev.wishlist.controller;

import com.banditdev.wishlist.repository.WishRepository;
import com.banditdev.wishlist.repository.WishlistRepository;
import com.banditdev.wishlist.service.WishService;
import com.banditdev.wishlist.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(WishController.class)
class WishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishService wishService;

    @MockitoBean
    private WishRepository wishRepository;

    @Test
    void showAddWishForm() {
    }

    @Test
    void addWish() {
    }

    @Test
    void deleteWish() {
    }

    @Test
    void updateWish() {
    }

    @Test
    void editWish() {
    }
}