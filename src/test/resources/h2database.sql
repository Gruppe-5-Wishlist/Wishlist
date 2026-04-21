DROP TABLE IF EXISTS wish;
DROP TABLE IF EXISTS wishlist;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       user_email VARCHAR(100) NOT NULL,
                       user_name VARCHAR(50) NOT NULL,
                       user_password VARCHAR(50) NOT NULL
);

CREATE TABLE wishlist (
                          wishlist_id INT AUTO_INCREMENT PRIMARY KEY,
                          wishlist_name VARCHAR(100) NOT NULL,
                          user_id INT NOT NULL,
                          CONSTRAINT fk_wishlist_user
                              FOREIGN KEY (user_id)
                                  REFERENCES users(user_id)
                                  ON DELETE CASCADE
);

CREATE TABLE wish (
                      wish_id INT AUTO_INCREMENT PRIMARY KEY,
                      wish_name VARCHAR(100) NOT NULL,
                      wish_description VARCHAR(400),
                      wish_link VARCHAR(200),
                      wish_price DOUBLE,
                      wishlist_id INT NOT NULL,
                      CONSTRAINT fk_wish_wishlist
                          FOREIGN KEY (wishlist_id)
                              REFERENCES wishlist(wishlist_id)
                              ON DELETE CASCADE
);



INSERT INTO users (user_email, user_name, user_password) VALUES
                                                             ('emma@mail.com', 'Emma', 'pass1234'),
                                                             ('jakob@mail.com', 'Jakob', 'securepwd'),
                                                             ('lina@mail.com', 'Lina', 'mypassword');

INSERT INTO wishlist (wishlist_name, user_id) VALUES
                                                  ('Birthday Gifts', 1),
                                                  ('Christmas List', 1),
                                                  ('Tech Gear', 2),
                                                  ('Travel Plans', 3);

INSERT INTO wish (wish_name, wish_description, wish_link, wish_price, wishlist_id) VALUES
                                                                                       ('Adidas Sneakers',   'White size 40',            'https://adidas.com/sneakers',      799.95, 1),
                                                                                       ('Kindle Paperwhite', 'E-reader with backlight',  'https://amazon.com/kindle',        1299.00, 1),
                                                                                       ('Nintendo Switch',   'OLED model',               'https://nintendo.com/switch',      2499.00, 2),
                                                                                       ('Mechanical Mouse',  'RGB gaming mouse',         'https://logitech.com/mouse',        499.00, 3),
                                                                                       ('4K Monitor',        '27 inch UHD display',      'https://dell.com/monitor',         1999.00, 3),
                                                                                       ('Backpack',          'Waterproof travel bag',    'https://osprey.com/backpack',       899.00, 4),
                                                                                       ('Travel Guide',      'Japan travel handbook',    'https://lonelyplanet.com',          249.00, 4);