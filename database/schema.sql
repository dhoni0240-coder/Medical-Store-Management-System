DROP DATABASE IF EXISTS medical_store_db;

CREATE DATABASE IF NOT EXISTS medical_store_db;
USE medical_store_db;

CREATE TABLE users(
user_id    INT AUTO_INCREMENT PRIMARY KEY,
full_name  VARCHAR(100) NOT NULL,
username   VARCHAR(50) UNIQUE NOT NULL,
password   VARCHAR(255) NOT NULL,
role       ENUM('ADMIN','PHARMACIST','STAFF') NOT NULL,
phone      VARCHAR(15),
email      VARCHAR(100),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE suppliers(
supplier_id INT AUTO_INCREMENT PRIMARY KEY,
supplier_name VARCHAR(100) NOT NULL,
phone VARCHAR(15),
email VARCHAR(100),
address TEXT,
company_name VARCHAR(100)
);

CREATE TABLE medicines(
medicine_id INT AUTO_INCREMENT PRIMARY KEY,
medicine_name VARCHAR(100) NOT NULL,
formula VARCHAR(100),
category VARCHAR(50),
batch_no VARCHAR(50) UNIQUE,
manufacture_date DATE,
expiry_date DATE,
purchase_price DECIMAL(10,2),
selling_price DECIMAL(10,2),
quantity_in_stock INT DEFAULT 0,
rack_no VARCHAR(20),
supplier_id INT,

FOREIGN KEY(supplier_id)
     REFERENCES suppliers(supplier_id)
);

CREATE TABLE customers(
customer_id INT AUTO_INCREMENT PRIMARY KEY,
customer_name VARCHAR(100) NOT NULL,
phone VARCHAR(15),
email VARCHAR(50),
address TEXT
);

CREATE TABLE bills(
bill_id INT AUTO_INCREMENT PRIMARY KEY,
bill_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
customer_id INT,
user_id INT,
total_amount DECIMAL(10,2),
discount DECIMAL(10,2) DEFAULT 0,
final_amount DECIMAL(10,2),

FOREIGN KEY(customer_id) REFERENCES customers(customer_id),
      FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE bill_items(
bill_item_id INT AUTO_INCREMENT PRIMARY KEY,
bill_id INT NOT NULL,
medicine_id INT NOT NULL,
quantity INT NOT NULL,
price DECIMAL(10,2) NOT NULL,
subtotal DECIMAL(10,2) NOT NULL,

FOREIGN KEY(bill_id) REFERENCES bills(bill_id),
      FOREIGN KEY(medicine_id) REFERENCES medicines(medicine_id)
);

CREATE TABLE stock_history(
stock_id INT AUTO_INCREMENT PRIMARY KEY,
medicine_id INT NOT NULL,
old_quantity INT,
new_quantity INT,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

FOREIGN KEY(medicine_id) REFERENCES medicines(medicine_id)
);

CREATE TABLE sales_history(
sale_id INT AUTO_INCREMENT PRIMARY KEY,
medicine_id INT NOT NULL,
quantity_sold INT NOT NULL,
sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

FOREIGN KEY(medicine_id) REFERENCES medicines(medicine_id)
);

SHOW TABLES;
DESC bill_items;
DESC bills;
DESC customers;
DESC medicines;
DESC sales_history;
DESC stock_history;
DESC suppliers;
DESC users;