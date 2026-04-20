package com.banditdev.wishlist.controller;

import com.banditdev.wishlist.model.Wishlist;
import com.banditdev.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }


    @PostMapping
    public Wishlist createwishlist(@RequestBody Wishlist wishlist, @RequestParam int userId) {
        return wishlistService.addWishlist(wishlist, userId);

    }
}
