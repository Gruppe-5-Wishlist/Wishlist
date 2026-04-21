package com.banditdev.wishlist.controller;

import com.banditdev.wishlist.model.User;
import com.banditdev.wishlist.model.Wishlist;
import com.banditdev.wishlist.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        model.addAttribute("wishlists", wishlists);

        return "wishlist";
    }

    @GetMapping("/{id}")
    public String viewWishlist(@PathVariable int id, HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login";
        }

        Wishlist wishlist = wishlistService.findWishlistById(id);

        User currentUser = (User) session.getAttribute("user");
        if (!wishlistService.validateWishlistOwner(currentUser, id)) {
            return "redirect:/wishlist";
        }

        if (wishlist == null) {
            return "redirect:/wishlist";
        }

        model.addAttribute("wishlist", wishlist);
        return "wish";
    }

    @PostMapping("/add")
    public String addWishlist(@RequestParam String wishlistName,
                              HttpSession session) {

        User currentUser = (User) session.getAttribute("user");

        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistName(wishlistName);

        wishlistService.addWishlist(wishlist, currentUser.getUserId());

        return "redirect:/wishlist";
    }

    @PostMapping("/delete/{id}")
    public String deleteWishlist(@PathVariable int id, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";

        if (!wishlistService.validateWishlistOwner(user, id)) {
            return "redirect:/wishlist";
        }

        wishlistService.deleteWishlistById(id);
        return "redirect:/wishlist";
    }

}
