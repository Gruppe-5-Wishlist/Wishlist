package com.banditdev.wishlist.controller;

import com.banditdev.wishlist.model.User;
import com.banditdev.wishlist.model.Wishlist;
import com.banditdev.wishlist.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public String index(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login";
        }

        User currentUser = (User) session.getAttribute("user");
        List<Wishlist> wishlists = wishlistService.findWishlistsByUserId(currentUser.getUserId());

        model.addAttribute(wishlists);

        return "wishlist";
    }
}
