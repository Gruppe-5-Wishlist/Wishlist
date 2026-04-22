package com.banditdev.wishlist.controller;

import com.banditdev.wishlist.model.User;
import com.banditdev.wishlist.model.Wish;
import com.banditdev.wishlist.model.Wishlist;
import com.banditdev.wishlist.repository.WishRepository;
import com.banditdev.wishlist.service.WishService;
import com.banditdev.wishlist.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishController.class)
class WishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishService wishService;

    @MockitoBean
    private WishlistService wishlistService;

    @Test
    void showAddWishForm() throws Exception {
        int wishId = 1;
        int wishlistId = 1;
        User user = new User(1, "email@test.com","TestName","TestPassword");

        MockHttpSession testSession = new MockHttpSession();
        testSession.setAttribute("user", user);

        when(wishlistService.validateWishlistOwner(any(), eq(wishlistId))).thenReturn(true);


        Wishlist mockWishlist = new Wishlist();
        mockWishlist.setWishlistId(wishlistId);

        when(wishlistService.findWishlistById(wishlistId)).thenReturn(mockWishlist);

        mockMvc.perform(get("/wish/add/{wishlistId}", wishlistId)
                        .session(testSession))
                .andExpect(status().isOk())
                .andExpect(view().name("addWish"));
    }

    @Test
    void addWish() throws Exception {
        int wishId = 1;
        int wishlistId = 1;
        User user = new User(1, "email@test.com","TestName","TestPassword");

        MockHttpSession testSession = new MockHttpSession();
        testSession.setAttribute("user", user);

        when(wishlistService.validateWishlistOwner(any(), eq(wishlistId))).thenReturn(true);

        mockMvc.perform(post("/wish/add/{id}", wishlistId)
                        .session(testSession)
                        .param("wishName", "Test Wish")
                        .param("wishDescription", "WishDesc")
                        .param("wishLink", "http://testlink.dk")
                        .param("wishPrice", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlist/" + wishlistId));
    }

    @Test
    void deleteWish() throws Exception {
        int wishId = 1;
        int wishlistId = 1;
        User user = new User(1, "email@test.com","TestName","TestPassword");

        MockHttpSession testSession = new MockHttpSession();
        testSession.setAttribute("user", user);

        when(wishService.validateWishOwner(any(), eq(wishId))).thenReturn(true);

        mockMvc.perform(post("/wish/delete/{wishId}", wishId)
                .sessionAttr("user",user).param("wishlistId", String.valueOf(wishlistId)))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/wishlist/"+wishlistId));

    }

    @Test
    void updateWish() throws Exception {
        int wishId = 1;
        int wishlistId = 1;
        User user = new User(1, "email@test.com","TestName","TestPassword");

        MockHttpSession testSession = new MockHttpSession();
        testSession.setAttribute("user", user);

        when(wishlistService.validateWishlistOwner(any(), eq(wishlistId))).thenReturn(true);
        when(wishService.validateWishOwner(any(), eq(wishId))).thenReturn(true);

        mockMvc.perform(post("/wish/update/{wishId}", wishId).sessionAttr("user",user)
                .param("wishName","updatedName")
                .param("wishDescription","updatedDescription")
                .param("wishLink","updatedLink").param("wishPrice",String.valueOf(10))
                .param("wishlistId",String.valueOf(wishlistId)))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/wishlist/"+wishlistId));

    }

    @Test
    void editWish() throws Exception {
        int wishId = 1;
        int wishlistId = 1;
        User user = new User(1, "email@test.com","TestName","TestPassword");

        MockHttpSession testSession = new MockHttpSession();
        testSession.setAttribute("user", user);

        when(wishlistService.validateWishlistOwner(any(), eq(wishlistId))).thenReturn(true);
        when(wishService.validateWishOwner(any(), eq(wishId))).thenReturn(true);


        Wish testWish = new Wish();
        testWish.setWishId(wishId);
        testWish.setWishlistId(wishlistId);

        when(wishService.findWishById(wishId)).thenReturn(testWish);

        mockMvc.perform(get("/wish/edit/{wishId}", wishId)
                        .session(testSession))
                .andExpect(status().isOk())
                .andExpect(view().name("editWish"))
                .andExpect(model().attributeExists("wish"))
                .andExpect(model().attribute("wish", testWish));
    }
}