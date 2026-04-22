package com.banditdev.wishlist.controller;

import com.banditdev.wishlist.model.User;
import com.banditdev.wishlist.model.Wishlist;
import com.banditdev.wishlist.repository.WishlistRepository;
import com.banditdev.wishlist.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishlistController.class)
class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishlistService wishlistService;

    @MockitoBean
    private WishlistRepository wishlistRepository;

    //Redirects when no user in session
    @Test
    void index_shouldRedirectToLogin_whenUserNotInSession() throws Exception {
        mockMvc.perform(get("/wishlist"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/login"));
    }

    //return wishlist view when user exist
    @Test
    void index_shouldReturnWishlistView_whenUserInSession() throws Exception {
        User mockUser = new User();
        mockUser.setUserId(1);

        List<Wishlist> mockWishlists = List.of(new Wishlist(), new Wishlist());

        when(wishlistService.findWishlistsByUserId(1))
                .thenReturn(mockWishlists);

        mockMvc.perform(get("/wishlist")
                        .sessionAttr("user", mockUser))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist"))
                .andExpect(model().attributeExists("wishlists"))
                .andExpect(model().attribute("wishlists", mockWishlists));
    }

    //Verify service call
    @Test
    void index_shouldCallServiceWithCorrectUserId() throws Exception {
        User mockUser = new User();
        mockUser.setUserId(42);

        when(wishlistService.findWishlistsByUserId(42))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/wishlist")
                        .sessionAttr("user", mockUser))
                .andExpect(status().isOk());

        verify(wishlistService).findWishlistsByUserId(42);
    }
}
