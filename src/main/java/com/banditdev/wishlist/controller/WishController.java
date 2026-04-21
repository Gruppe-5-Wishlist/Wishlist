package com.banditdev.wishlist.controller;

import com.banditdev.wishlist.model.User;
import com.banditdev.wishlist.model.Wish;
import com.banditdev.wishlist.service.WishService;
import com.banditdev.wishlist.service.WishlistService;
import jakarta.servlet.http.HttpSession;
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
    public String showAddWishForm(@PathVariable int wishlistId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";

        if (!wishlistService.validateWishlistOwner(user, wishlistId)) {
            return "redirect:/wishlist";
        }

        model.addAttribute("wishlist", wishlistService.findWishlistById(wishlistId));
        return "addWish";
    }

    @PostMapping("/add/{wishlistId}")
    public String addWish(@ModelAttribute Wish wish, @PathVariable int wishlistId, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";

        if (!wishlistService.validateWishlistOwner(user, wishlistId)) {
            return "redirect:/wishlist";
        }

        wishService.addWish(wish, wishlistId);
        return "redirect:/wishlist/" + wishlistId;
    }

    @PostMapping("/delete/{wishId}")
    public String deleteWish(@PathVariable int wishId, @RequestParam int wishlistId, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";

        if (!wishService.validateWishOwner(user, wishId)) {
            return "redirect:/wishlist";
        }

        wishService.deleteWishById(wishId);
        return "redirect:/wishlist/" + wishlistId;
    }

    @PostMapping("/update/{wishId}")
    public String updateWish(@ModelAttribute Wish wish, @PathVariable int wishId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";

        int wishlistId = wish.getWishlistId();

        if (!wishlistService.validateWishlistOwner(user, wishlistId) || !wishService.validateWishOwner(user, wishId)) {
            return "redirect:/wishlist";
        }

        wish.setWishId(wishId);
        wishService.updateWish(wish);

        return "redirect:/wishlist/" + wishlistId;
    }

    @GetMapping("/edit/{wishId}")
    public String editWish(@PathVariable int wishId, Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";

        Wish wish = wishService.findWishById(wishId);

        if (!wishlistService.validateWishlistOwner(user, wish.getWishlistId()) || !wishService.validateWishOwner(user, wishId)) {
            return "redirect:/wishlist";
        }

        model.addAttribute("wish", wish);
        return "editWish";
    }
}