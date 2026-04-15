package com.banditdev.wishlist.service;

import com.banditdev.wishlist.model.Wish;
import com.banditdev.wishlist.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {
    private final WishRepository wishRepository;
    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<Wish> findAllWishes() {
        return wishRepository.findAllWishes();
    }

    public Wish addWish(Wish wish) {
        return wishRepository.addWish(wish);
    }

    public void deleteWishById(int idToDelete) {
        wishRepository.deleteWishById(idToDelete);
    }

    public Wish findWishById(int idToFind) {
        return wishRepository.findWishById(idToFind);
    }

    public void updateWish(Wish wish) {
        wishRepository.updateWish(wish);
    }
}
