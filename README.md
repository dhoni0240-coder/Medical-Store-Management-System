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

# 📌 Roadmap

## 🔐 Version 3.1 — User Authentication & Access Control

- [ ] User Login System
- [ ] Username & Password Validation
- [ ] User Session Management
- [ ] Logout
- [ ] Admin & Cashier Roles
- [ ] Role-Based Access Control

---

## 🧾 Version 3.2 — Invoice & Document Management

- [ ] Invoice Generation
- [ ] PDF Invoice Generation
- [ ] Printable Invoice
- [ ] Purchase Invoice

---

## 💾 Version 3.3 — Backup & Security

- [ ] Database Backup
- [ ] Database Restore
- [ ] Password Security
- [ ] Input Validation
- [ ] Improved Exception Handling

---

## 🚀 Version 4.0 — Production Ready

- [ ] Complete Admin Dashboard
- [ ] Cashier Dashboard
- [ ] Advanced Reports
- [ ] Performance Optimization
- [ ] Final Testing
- [ ] Production Release

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