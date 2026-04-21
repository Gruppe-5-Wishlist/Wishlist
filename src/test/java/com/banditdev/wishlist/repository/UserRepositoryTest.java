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
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void addUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void findUserByEmail() {
    }

    @Test
    void findUserById() {
    }

    @Test
    void updateUser() {
    }
}