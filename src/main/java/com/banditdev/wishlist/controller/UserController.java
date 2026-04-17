package com.banditdev.wishlist.controller;

import com.banditdev.wishlist.model.User;
import com.banditdev.wishlist.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/authenticateLogin")
    public String login(@RequestParam("ue") String userEmail, @RequestParam("upw") String userPassword, HttpSession session, Model model) {

        if (userService.validateUser(userEmail, userPassword) != null) {
            session.setAttribute("user", userService.validateUser(userEmail, userPassword));
            return "redirect:/wishlist";
        } else {
            model.addAttribute("wrongCredentials", true);
            return "login";
        }
    }
}
