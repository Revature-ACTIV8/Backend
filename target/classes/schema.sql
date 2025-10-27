/* this basically creates the db and the schema for the db for the payments */

CREATE DATABASE IF NOT EXISTS ACTIV8_DB;

USE ACTIV8_DB;

CREATE TABLE IF NOT EXISTS payments (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    payment_date DATETIME NOT NULL,
    amount DECIMAL(18, 2) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    payment_status VARCHAR(50) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);