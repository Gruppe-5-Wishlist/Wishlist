package com.banditdev.wishlist.controller;

import com.banditdev.wishlist.service.WishService;
import com.banditdev.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(WishControllerTest.class)
@ActiveProfiles
class WishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishService wishService;

    @MockitoBean
    private WishlistService wishlistService;
}

