package com.banditdev.wishlist.service;

import com.banditdev.wishlist.model.User;
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

    public Wish addWish(Wish wish, int wishlistId) {
        return wishRepository.addWish(wish, wishlistId);
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

    public boolean validateWishOwner(User user, int wishId) {
        Wish wish = wishRepository.findWishById(wishId);
        return wish != null && wishRepository.findWishesByUserId(user.getUserId())
                .stream().anyMatch(w -> w.getWishId() == wishId);
    }

    public List<Wish> findWishesByUserId(int userId) {
        return wishRepository.findWishesByUserId(userId);
    }

}
