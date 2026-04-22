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
                          CONSTRAINT fk_wishlist_user
                              FOREIGN KEY (user_id) REFERENCES user(user_id)
                                  ON DELETE CASCADE
);

CREATE TABLE wish (
                      wish_id          INT AUTO_INCREMENT PRIMARY KEY,
                      wish_name        VARCHAR(100) NOT NULL,
                      wish_description VARCHAR(400),
                      wish_link        VARCHAR(200),
                      wish_price       DOUBLE,
                      wishlist_id      INT NOT NULL,
                      CONSTRAINT fk_wish_wishlist
                          FOREIGN KEY (wishlist_id) REFERENCES wishlist(wishlist_id)
                              ON DELETE CASCADE
);

INSERT INTO user (user_email, user_name, user_password) VALUES
                                                            ('alice@mail.com', 'Alice', 'password123'),
                                                            ('bob@mail.com',   'Bob',   'hunter2'),
                                                            ('carl@mail.com',  'Carl',  'qwerty99');

INSERT INTO wishlist (wishlist_name, user_id) VALUES
                                                  ('Fødselsdag',   1),
                                                  ('Jul',          1),
                                                  ('Gamer ønsker', 2),
                                                  ('Rejse ting',   3);

INSERT INTO wish (wish_name, wish_description, wish_link, wish_price, wishlist_id) VALUES
                                                                                       ('Nike Air Max',   'Str. 42, sort',           'https://nike.com/airmax',         899.95,  1),
                                                                                       ('Lego Technic',   'Det store sæt',           'https://lego.com/technic',        599.00,  1),
                                                                                       ('PS5 controller', 'Hvid extra controller',   'https://playstation.com/',         699.00,  2),
                                                                                       ('Gaming headset', 'Trådløst, sort',          'https://steelseries.com/',         1299.00, 3),
                                                                                       ('Keyboard mech',  'Cherry MX Red switches',  'https://keychron.com/',            899.00,  3),
                                                                                       ('Rejsepude',      'Til lange flyture',       'https://amazon.com/travelpillow', 149.00,  4),
                                                                                       ('Rejsedagbog',    'Hardcover, A5',           'https://moleskine.com/',           199.00,  4);