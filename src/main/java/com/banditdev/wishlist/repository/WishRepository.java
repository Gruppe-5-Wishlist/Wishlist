package com.banditdev.wishlist.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //TODO List<Object> findAll() {}

    //TODO public Object addObject(Object obj) {}

    //TODO public void deleteObjectById(int idToDelete) {}

    //TODO public Object findObjectById(int idToFind) {}

    //TODO public void updateObject(Object obj) {}
}
