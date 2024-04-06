use cms;

INSERT INTO category (id, name, description) VALUES
(1, 'Electronics', 'Devices and gadgets for everyday use.'),
(2, 'Books', 'A wide range of reading materials in various genres.'),
(3, 'Home Appliances', 'Essentials for home maintenance and comfort.');

INSERT INTO product (id, category_id, name, description, price) VALUES
(1, 1, 'Smartphone', 'Latest model with high-end specifications.', 999.99),
(2, 1, 'Laptop', 'Lightweight and powerful, perfect for professionals on the go.', 1299.99),
(3, 2, 'Fantasy Novel', 'A gripping tale of adventure and discovery.', 19.99);

INSERT INTO site (id, name, logo) VALUES
(1, 'WEBSITE', 'LOGO');

INSERT INTO `user` (id, username, role, password) VALUES
(1, 'admin', 'ADMIN', '$2b$04$BBvwZZ06RFQxwb6.clulQOSh4rLlzErWns5YnfuMpOhqHw5TM/q82'),
(2, 'user', 'USER', '$2b$04$BBvwZZ06RFQxwb6.clulQOSh4rLlzErWns5YnfuMpOhqHw5TM/q82');

