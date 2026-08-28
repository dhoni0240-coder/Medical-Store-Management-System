# 🏥 Medical Store Management System

A Java-based console application developed to automate the daily operations of a medical store. The system manages medicines, suppliers, customers, billing, inventory, and sales using Java, JDBC, and MySQL.

---

# 📖 Overview

The Medical Store Management System is designed to simplify medical store operations by providing an efficient way to manage medicines, suppliers, customers, billing, inventory, and sales.

This project follows a layered architecture using Java, JDBC, and MySQL, making it easy to maintain, scale, and extend.

---

# ✨ Features

## ✅ Medicine Management

- View All Medicines
- Add New Medicine
- Update Medicine
- Delete Medicine
- Search Medicine by:
  - ID
  - Name
  - Category
  - Formula
  - Batch Number

---

## ✅ Supplier Management

- View All Suppliers
- Add Supplier
- Update Supplier
- Delete Supplier
- Search Supplier by:
  - ID
  - Name
  - Phone Number

---

## ✅ Customer Management

- View All Customers
- Add Customer
- Update Customer
- Delete Customer
- Search Customer by:
  - ID
  - Name
  - Phone Number
  - Email

---

## ✅ Billing System

- Generate Bills
- Multiple Medicine Billing
- Automatic Stock Update
- Discount Calculation
- Bill Item Management

---

## ✅ Bill History

- View All Bills
- View Bill by Bill ID
- Search Bills by Customer Name
- View Purchased Medicines for Each Bill

---

## ✅ Inventory Reports

- Low Stock Medicines
- Out of Stock Medicines
- Expiring Medicines (Next 30 Days)

---

## ✅ Sales Reports

- Daily Sales Report
- Monthly Sales Report
- Top Selling Medicines
- Profit Report

---

# 🛠 Technologies Used

- Java
- JDBC
- MySQL
- Maven
- Git
- GitHub
- IntelliJ IDEA

---

# 🏗 Project Structure

```text
Medical-Store-Management-System
│
├── src
│   ├── app
│   │     Main.java
│   │
│   ├── config
│   │     DatabaseConnection.java
│   │
│   ├── model
│   │     Medicine.java
│   │     Supplier.java
│   │     Customer.java
│   │     Bill.java
│   │     BillItem.java
│   │    
│   │
│   ├── dao
│   │     MedicineDAO.java
│   │     SupplierDAO.java
│   │     CustomerDAO.java
│   │     BillingDAO.java
│   │     ReportDAO.java
│   │
│   ├── service
│   │     MedicineService.java
│   │     SupplierService.java
│   │     CustomerService.java
│   │     BillingService.java
│   │     ReportService.java
│   │
│   ├── menu
│   │     MedicineMenu.java
│   │     SupplierMenu.java
│   │     CustomerMenu.java
│   │     BillingMenu.java
│   │     ReportsMenu.java
│   │
│   └── exception
│
├── schema.sql
├── sample-data.sql
├── pom.xml
└── README.md
```

---

# 🗄 Database

Current Database Tables:

- medicines
- suppliers
- customers
- bills
- bill_items
- stock_history
- sales_history
- purchases
- purchase_items

---

# 🚀 Current Progress

## ✅ Version 3.0 (Current)

✔ Medicine Module

✔ Supplier Module

✔ Customer Module

✔ Billing Module

✔ Bill History

✔ Inventory Reports

✔ Sales Reports

✔ Purchase Management

✔ Purchase History

✔ Purchase Details

✔ Automatic Stock Update

✔ Database Transaction Management for Purchases

---

# 📌 Project Roadmap

## ✅ Version 1.0 — Core Management

* [✔] Medicine Management
* [✔] Supplier Management
* [✔] Customer Management

---

## ✅ Version 2.0 — Billing & Analytics

* [✔] Billing Management
* [✔] Bill History
* [✔] Inventory Reports
* [✔] Sales Reports
* [✔] Profit Analysis

---

## ✅ Version 3.0 — Purchase Management

* [✔] Purchase Management
* [✔] Purchase History
* [✔] Stock Entry
* [✔] Supplier Purchase Tracking
* [✔] Automatic Stock Updates

---

## 🚧 Version 3.1 — Authentication, Access Control & Dashboard

### 🔐 Authentication

* [✔] User Login System
* [✔] Username & Password Validation
* [✔] User Session Management
* [✔] Logout

### 👥 User Roles

* [✔] ADMIN Role
* [✔] PHARMACIST Role
* [✔] STAFF Role
* [✔] Role-Based Access Control
* [✔] User Management
* [✔] Add New Users

### 🛡️ Module-Level Access Control

* [✔] Medicine Access Control
* [✔] Supplier Access Control
* [✔] Customer Access Control
* [✔] Billing Access Control
* [✔] Purchase Access Control
* [✔] Reports Access Control
* [✔] User Management Access Control

---

## 📋 Version 3.2 — Invoice & Document Management

### 📊 Dashboard
* [ ] Admin Dashboard
* [ ] Pharmacist Dashboard
* [ ] Staff Dashboard
* [ ] Sales Summary
* [ ] Inventory Summary
* [ ] Low-Stock Summary
* [ ] Expiry Alerts
* [ ] Purchase Summary

### Invoice Management
* [ ] Invoice Generation
* [ ] PDF Invoice Generation
* [ ] Printable Invoice
* [ ] Purchase Invoice
* [ ] Invoice History
* [ ] Improved Invoice Formatting

---

## 📋 Version 3.3 — Backup, Security & Reliability

* [ ] Database Backup
* [ ] Database Restore
* [ ] Password Hashing
* [ ] Input Validation
* [ ] Improved Exception Handling
* [ ] Transaction Management
* [ ] Improved Database Security

---

## 🚀 Version 4.0 — Web & Production Version

### 🌐 Frontend

* [ ] Web-based User Interface
* [ ] React Frontend
* [ ] Responsive Design
* [ ] Interactive Dashboard
* [ ] Login Interface
* [ ] Role-Based UI

### ⚙️ Backend

* [ ] Spring Boot Backend
* [ ] REST APIs
* [ ] API-Based Architecture
* [ ] Frontend–Backend Integration

### 📈 Advanced Features

* [ ] Advanced Reports
* [ ] Data Visualization
* [ ] Excel Export
* [ ] Performance Optimization
* [ ] Final Testing

### ☁️ Production

* [ ] Deployment
* [ ] Production Database Configuration
* [ ] Production Release
* [ ] Complete Documentation


# 📚 Concepts Implemented

- Object-Oriented Programming (OOP)
- JDBC Connectivity
- MySQL Database
- DAO Design Pattern
- CRUD Operations
- Search Operations
- Java Collections Framework
- Exception Handling
- Layered Architecture
- SQL JOIN
- GROUP BY
- Aggregate Functions (SUM, COUNT)
- Report Generation

---

# 🚧 Project Status

🟢 **Version 3.1 In Development**

Current Progress: **Approximately 65% Complete**

---

# 👨‍💻 Author

**Ashirvad Shukla**

B.Tech Computer Science Engineering

Aspiring Java Backend Developer

GitHub: https://github.com/dhoni0240-coder