CREATE DATABASE IF NOT EXISTS wishlist_db;
USE wishlist_db;

DROP TABLE IF EXISTS wish;
DROP TABLE IF EXISTS wishlist;
DROP TABLE IF EXISTS user;

CREATE TABLE user (
                      user_id       INT AUTO_INCREMENT PRIMARY KEY,
                      user_email    VARCHAR(100) NOT NULL,
                      user_name     VARCHAR(50)  NOT NULL,
                      user_password VARCHAR(50)  NOT NULL
);

CREATE TABLE wishlist (
                          wishlist_id   INT AUTO_INCREMENT PRIMARY KEY,
                          wishlist_name VARCHAR(100) NOT NULL,
                          user_id       INT NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES user(user_id)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE
);

CREATE TABLE wish (
                      wish_id          INT AUTO_INCREMENT PRIMARY KEY,
                      wish_name        VARCHAR(100) NOT NULL,
                      wish_description VARCHAR(400),
                      wish_link        VARCHAR(200),
                      wish_price       DOUBLE,
                      wishlist_id      INT NOT NULL,
                      FOREIGN KEY (wishlist_id) REFERENCES wishlist(wishlist_id)
                          ON DELETE CASCADE
                          ON UPDATE CASCADE
);