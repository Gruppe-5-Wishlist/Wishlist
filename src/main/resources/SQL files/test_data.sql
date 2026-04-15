USE wishlist_db;

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