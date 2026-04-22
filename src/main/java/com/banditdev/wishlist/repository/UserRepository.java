package com.banditdev.wishlist.repository;

import com.banditdev.wishlist.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User addUser(User user) {
        String sql = """
                INSERT INTO user (user_id, user_email, user_name, user_password) VALUES (?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getUserEmail());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getUserPassword());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new IllegalStateException("Failed to keyholder id");
        }
        return new User(key.intValue(), user.getUserEmail(), user.getUserName(), user.getUserPassword());
    }

    public void deleteUser(int userID) {
        String sql = """
                DELETE FROM user
                WHERE user_id = ?
                """;
        jdbcTemplate.update(sql, userID);
    }

    public User findUserByEmail(String userEmail) {
        String sql = """
                SELECT
                    user_id,
                    user_email,
                    user_name,
                    user_password
                FROM user
                WHERE LOWER(user_email) = LOWER(?)
                """;

        try {
            return
                    jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                                    new User(
                                            rs.getInt("user_id"),
                                            rs.getString("user_email"),
                                            rs.getString("user_name"),
                                            rs.getString("user_password")
                                    ),
                            userEmail
                    );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User findUserById(int id) {
        String sql = """
                SELECT
                    user_id,
                    user_email,
                    user_name,
                    user_password
                FROM user
                WHERE user_id = ?
                """;
        try {
            return
                    jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                                    new User(
                                            rs.getInt("user_id"),
                                            rs.getString("user_email"),
                                            rs.getString("user_name"),
                                            rs.getString("user_password")
                                    ),
                            id
                    );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public void updateUser(User user) {
        String sql = """
                        UPDATE user
                        SET user_email = ?, user_name = ?, user_password = ?
                        WHERE user_id = ?
                """;

        jdbcTemplate.update(
                sql,
                user.getUserEmail(),
                user.getUserName(),
                user.getUserPassword(),
                user.getUserId()
        );
    }
}

//TODO - Make getUser() and add an ArrayList