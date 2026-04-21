package com.banditdev.wishlist.repository;

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
class WishRepositoryTest {


    @Autowired
    private WishRepository repository;

    @Test
    void findAllWishes() {
    }

    @Test
    void addWish() {
    }

    @Test
    void deleteWishById() {
    }

    @Test
    void findWishById() {
    }

    @Test
    void updateWish() {
    }
}