package com.banditdev.wishlist.model;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {
    private int wishlistId;
    private String wishlistName;
    private List<Wish> wishes;

    public Wishlist() {
    }

    public Wishlist(int wishlistId, String wishlistName) {
        this.wishlistId = wishlistId;
        this.wishlistName = wishlistName;
        this.wishes = new ArrayList<>();
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void addWish(Wish wish) {
        wishes.add(wish);
    }

    public void removeWish(Wish wish) {
        wishes.remove(wish);
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }
}