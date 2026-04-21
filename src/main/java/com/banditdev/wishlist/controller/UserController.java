package com.banditdev.wishlist.controller;

import com.banditdev.wishlist.model.User;
import com.banditdev.wishlist.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        if (userService.validateUser(userEmail, userPassword)) {
            session.setAttribute("user", userService.findUserByEmail(userEmail));
            return "redirect:/wishlist";
        } else {
            model.addAttribute("wrongCredentials", true);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }


    @GetMapping("/profile")
    public String editProfile(HttpSession session, Model model) {

        if (isLoggedIn(session) == true) {
            User currentUser = (User) session.getAttribute("user");
            model.addAttribute("user", currentUser);
            return "editProfile";
        } else {
            session.invalidate();
            return "index";
        }
    }

    @PostMapping("/saveProfile")
    public String saveProfile(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/profile";
    }


}
