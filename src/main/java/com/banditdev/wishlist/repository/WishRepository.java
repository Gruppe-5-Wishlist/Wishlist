package com.banditdev.wishlist.repository;

import com.banditdev.wishlist.model.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class WishRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wish> findAllWishes() {

        String sql = """
                SELECT
                    wish.wish_id,
                    wish.wish_name,
                    wish.wish_description,
                    wish.wish_link,
                    wish.wish_price, 
                    wish.wishlist_id
                FROM wish
                JOIN wishlist wl ON wish.wishlist_id = wl.wishlist_id
                JOIN user u ON wl.user_id = u.user_id
                ORDER BY 
                    u.user_id,
                    wl.wishlist_id,
                    wish.wish_id;
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Wish(
                        rs.getInt("wish_id"),
                        rs.getString("wish_name"),
                        rs.getString("wish_description"),
                        rs.getString("wish_link"),
                        rs.getDouble("wish_price"),
                        rs.getInt("wishlist_id")

                )
        );
    }


    public Wish addWish(Wish wish, int wishlistId) {
        String sql = """
                INSERT INTO wishlist_db.wish (wish_name, wish_description, wish_link, wish_price, wishlist_id)
                VALUES (?, ?, ?, ?, ?)
                """;

        KeyHolder kh = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, wish.getWishName());
            ps.setString(2, wish.getWishDescription());
            ps.setString(3, wish.getWishLink());
            ps.setDouble(4, wish.getWishPrice());
            ps.setInt(5, wishlistId);
            return ps;
        }, kh);

        Number key = kh.getKey();
        if (key == null) {
            throw new IllegalStateException("Failed to get KeyHolder id.");
        }


        return new Wish(key.intValue(), wish.getWishName(), wish.getWishDescription(), wish.getWishLink(), wish.getWishPrice(), wishlistId);
    }


    public void deleteWishById(int idToDelete) {
        String sql = """
                DELETE FROM wish
                WHERE wish_id = ?
                """;
        jdbcTemplate.update(sql, idToDelete);
    }


    public Wish findWishById(int idToFind) {
        String sql = """
                
                        SELECT
                    wish_id,
                    wish_name,
                    wish_description,
                    wish_link,
                    wish_price
                FROM wish
                WHERE wish_id = ?
                """;

        return
                jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                                new Wish(
                                        rs.getInt("wish_id"),
                                        rs.getString("wish_name"),
                                        rs.getString("wish_description"),
                                        rs.getString("wish_link"),
                                        rs.getDouble("wish_price"),
                                        rs.getInt("wishlist_id")
                                ),
                        idToFind
                );
    }


    public void updateWish(Wish wish) {
        String sql = """
                UPDATE wish
                SET wish_name = ?, wish_description = ?, wish_link = ?, wish_price = ?
                WHERE wish_id = ?
                """;

        jdbcTemplate.update(
                sql,
                wish.getWishName(),
                wish.getWishDescription(),
                wish.getWishLink(),
                wish.getWishPrice(),
                wish.getWishId()
        );
    }

}
