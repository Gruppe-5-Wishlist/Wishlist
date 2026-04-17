package com.banditdev.wishlist.repository;

import com.banditdev.wishlist.model.Wish;
import com.banditdev.wishlist.model.Wishlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WishlistRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wishlist> findAllWishlists() {
        String sql = """
            SELECT
                wl.wishlist_id,
                wl.wishlist_name,
                w.wish_id,
                w.wish_name,
                w.wish_description,
                w.wish_link,
                w.wish_price
            FROM wishlist wl
            LEFT JOIN wish w
                ON wl.wishlist_id = w.wishlist_id
            """;

        return jdbcTemplate.query(sql, rs -> {
            Map<Integer, Wishlist> map = new HashMap<>();

            while (rs.next()) {
                int wishlistId = rs.getInt("wishlist_id");

                Wishlist wishlist = map.get(wishlistId);

                if (wishlist == null) {
                    wishlist = new Wishlist(
                            wishlistId,
                            rs.getString("wishlist_name")
                    );
                    map.put(wishlistId, wishlist);
                }
                int wishId = rs.getInt("wish_id");

                if (!rs.wasNull()) {
                    Wish wish = new Wish(
                            wishId,
                            rs.getString("wish_name"),
                            rs.getString("wish_description"),
                            rs.getString("wish_link"),
                            rs.getDouble("wish_price"),
                            rs.getInt("wishlist_id")
                    );

                    wishlist.addWish(wish);
                }
            }

            return new ArrayList<>(map.values());
        });
    }

    public void deleteWishlistById(int id) {
        String sql = """
                DELETE FROM wishlist
                WHERE wishlist_id = ?
                """;
        jdbcTemplate.update(sql, id);
    }

    public Wishlist addWishlist(Wishlist wishlist, int userId) {
        String sql = """
                INSERT INTO wishlist (wishlist_name, user_id)
                VALUES (?, ?)
                """;

        KeyHolder kh = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, wishlist.getWishlistName());
            ps.setInt(2, userId);
            return ps;
        }, kh);

        Number key = kh.getKey();
        if (key == null) {
            throw new IllegalStateException("Failed to get KeyHolder id.");
        }

        return new Wishlist(key.intValue(), wishlist.getWishlistName());
    }

    public Wishlist findWishlistById(int id) {
        String sql = """
            SELECT
                wl.wishlist_id,
                wl.wishlist_name,
                w.wish_id,
                w.wish_name,
                w.wish_description,
                w.wish_link,
                w.wish_price
                            FROM wishlist wl
                            LEFT
                JOIN wish w
                ON wl.
                    wishlist_id = w.wishlist_id
                WHERE wl.wishlist_id = ?
            """;

        return jdbcTemplate.query(sql, rs -> {
            Wishlist wishlist = null;

            while (rs.next()) {
                if (wishlist == null) {
                    wishlist = new Wishlist(
                            rs.getInt("wishlist_id"),
                            rs.getString("wishlist_name")
                    );
                }

                int wishId = rs.getInt("wish_id");
                if (!rs.wasNull()) {
                    wishlist.addWish(new Wish(
                            wishId,
                            rs.getString("wish_name"),
                            rs.getString("wish_description"),
                            rs.getString("wish_link"),
                            rs.getDouble("wish_price"),
                            rs.getInt("wishlist_id")
                    ));
                }
            }

            return wishlist;
        }, id);
    }

    public void updateWishlist(Wishlist wishlist) {
        String sql = """
            UPDATE wishlist
            SET wishlist_name = ?
            WHERE wishlist_id = ?
            """;

        jdbcTemplate.update(
                sql,
                wishlist.getWishlistName(),
                wishlist.getWishlistId()
        );

    }
}
