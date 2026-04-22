package com.banditdev.wishlist.repository;

import com.banditdev.wishlist.model.Wish;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2database.sql", executionPhase = BEFORE_TEST_METHOD)
class WishRepositoryTest {


    @Autowired
    private WishRepository repository;

    @Test
    void addWish() {
        int wishListId = 2;
        int wishId = 8;
        Wish saveWish = repository.addWish(new Wish(wishId, "testName", "Test Description", "test@link.dk", 100.0, wishListId), wishListId);

        Wish testWish = repository.findWishById(saveWish.getWishId());


        assertThat(testWish.getWishName()).isEqualTo("testName");
    }

    @Test
    void deleteWishById() {
        int wishListId = 1;
        int wishId = 8;
        Wish testWish = new Wish(wishId, "testName", "Test Description", "test@link.dk", 100.0, wishListId);


        assertThat(repository.findAllWishes().size()).isEqualTo(7);
        repository.deleteWishById(1);

        assertThat(repository.findAllWishes().size()).isEqualTo(6);
        assertThat(repository.findAllWishes().getFirst().getWishId()).isEqualTo(2);
    }

    @Test
    void updateWish() {
        Wish updatedWish = new Wish();
        updatedWish.setWishId(1);
        updatedWish.setWishName("Updated Name");
        updatedWish.setWishDescription("Updated description");
        updatedWish.setWishLink("https://www.example.com/updated-link");
        updatedWish.setWishPrice(100.00);

        repository.updateWish(updatedWish);

        Wish fetchedWish = repository.findWishById(1);
        assertEquals("Updated Name", fetchedWish.getWishName());
        assertEquals("Updated description", fetchedWish.getWishDescription());
        assertEquals(100.00, fetchedWish.getWishPrice());
        assertEquals("https://www.example.com/updated-link", fetchedWish.getWishLink());
    }
}