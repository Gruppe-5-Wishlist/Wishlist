package com.banditdev.wishlist.controller;

import com.banditdev.wishlist.model.Wish;
import com.banditdev.wishlist.service.WishService;
import com.banditdev.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wish")
public class WishController {

    private final WishService wishService;
    private final WishlistService wishlistService;

    public WishController(WishService wishService, WishlistService wishlistService) {
        this.wishService = wishService;
        this.wishlistService = wishlistService;
    }

    @GetMapping("/add/{wishlistId}")
    public String showAddWishForm(@PathVariable int wishlistId, Model model) {
        model.addAttribute("wishlist", wishlistService.findWishlistById(wishlistId));
        return "addWish";
    }

    @PostMapping("/add/{wishlistId}")
    public String addWish(@ModelAttribute Wish wish, @PathVariable int wishlistId) {
        wishService.addWish(wish, wishlistId);
        return "redirect:/wishlist/" + wishlistId;
    }

    @PostMapping("/delete/{wishId}")
    public String deleteWish(@PathVariable int wishId, @RequestParam int wishlistId) {
        wishService.deleteWishById(wishId);
        return "redirect:/wishlist/" + wishlistId;
    }

    @PostMapping("/update/{wishId}")
    public String updateWish(@ModelAttribute Wish wish, @PathVariable int wishId) {
        wish.setWishId(wishId);
        wishService.updateWish(wish);
        return "redirect:/wishlist/" + wish.getWishlistId();
    }
}