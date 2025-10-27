/* this basically creates the db and the schema for the db for the payments */

CREATE DATABASE IF NOT EXISTS ACTIV8_DB;

USE ACTIV8_DB;

-- CREATE TABLE IF NOT EXISTS cart (
--     order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     customer_id BIGINT NOT NULL
-- );

-- CREATE TABLE IF NOT EXISTS cart_items (
--     cart_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     order_id BIGINT NOT NULL,
--     product_id BIGINT NOT NULL,
--     quantity INT NOT NULL,
--     price DECIMAL(10, 2) NOT NULL,
--     FOREIGN KEY (order_id) REFERENCES cart(order_id) ON DELETE CASCADE
-- );

-- CREATE TABLE IF NOT EXISTS orders (
--     order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     customer_id BIGINT NOT NULL,
--     order_amount DECIMAL(10, 2) NOT NULL,
--     shipping_address VARCHAR(255) NOT NULL,
--     order_email VARCHAR(255) NOT NULL,
--     order_date DATETIME NOT NULL,
--     order_status VARCHAR(50) NOT NULL
-- );

-- CREATE TABLE IF NOT EXISTS order_items (
--     order_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     order_id BIGINT NOT NULL,
--     product_id BIGINT NOT NULL,
--     quantity INT NOT NULL,
--     price DECIMAL(10, 2) NOT NULL,

-- CREATE TABLE IF NOT EXISTS payments (
--     payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     order_id BIGINT NOT NULL,
--     payment_date DATETIME NOT NULL,
--     amount DECIMAL(18, 2) NOT NULL,
--     payment_method VARCHAR(50) NOT NULL,
--     payment_status VARCHAR(50) NOT NULL,
--     FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
-- );
