package com.banditdev.wishlist.service;

import com.banditdev.wishlist.model.Wishlist;
import com.banditdev.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wishlist> findAll() {
        return wishlistRepository.findAllWishlists();
    }

    public void updateWishlist(Wishlist wishlist) {
        wishlistRepository.updateWishlist(wishlist);
    }

    public List<Wishlist> findWishlistsByUserId(int id) {
        return wishlistRepository.findWishlistsByUserId(id);
    }

    public Wishlist addWishlist(Wishlist wishlist, int userId) {
        return wishlistRepository.addWishlist(wishlist, userId);
    }

    public void deleteWishlistById(int id) {
        wishlistRepository.deleteWishlistById(id);
    }
}
