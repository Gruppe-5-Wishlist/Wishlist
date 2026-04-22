package com.banditdev.wishlist.model;

import java.util.ArrayList;
import java.util.List;


public class User {
    private final List<Wishlist> wishlists = new ArrayList<>();
    private int userId;
    private String userEmail;
    private String userName;
    private String userPassword;


    public User() {
    }

    public User(int userId, String userEmail, String userName, String userPassword) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addWishlist(Wishlist wishlist) {
        wishlists.add(wishlist);
    }

    public void removeWishlist(Wishlist wishlist) {
        wishlists.remove(wishlist);
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

}
