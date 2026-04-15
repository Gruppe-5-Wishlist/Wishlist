package com.banditdev.wishlist.repository;

import com.banditdev.wishlist.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return List.of();
    }

    //TODO List<Object> findAll() {}

    //TODO public Object addObject(Object obj) {}

    //TODO public void deleteObjectById(int idToDelete) {}

    //TODO public Object findObjectById(int idToFind) {}

    //TODO public void updateObject(Object obj) {}
}
