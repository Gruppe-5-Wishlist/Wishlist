package com.banditdev.wishlist.repository;

import com.banditdev.wishlist.model.Wishlist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2database.sql", executionPhase = BEFORE_TEST_METHOD)
class WishlistRepositoryTest {

    @Autowired
    private WishlistRepository repository;

    @Test
    void deleteWishlistById() {
        Wishlist wishlist = new Wishlist(4, "Jul");
        repository.addWishlist(wishlist, 1);
        repository.deleteWishlistById(4);

        assertNull(repository.findWishlistById(4));
    }

    @Test
    void addWishlist() {
        Wishlist wishlist = new Wishlist(1, "Jul");
        Wishlist savedWishlist = repository.addWishlist(wishlist, 1);

        assertEquals("Jul", savedWishlist.getWishlistName());
        assertEquals(5, savedWishlist.getWishlistId());
    }

    @Test
    void updateWishlist() {
    }
}