# Software Requirements Specification (SRS)

# Medical Store Management System

**Version:** 3.1  
**Status:** In Development  
**Technology:** Java, JDBC, MySQL  
**Application Type:** Console-based Application

---

# 1. Introduction

## 1.1 Purpose

The Medical Store Management System is a Java-based console application designed to automate and manage the daily operations of a medical store.

The system provides functionality for:

- Medicine management
- Supplier management
- Customer management
- Billing
- Bill history
- Inventory monitoring
- Sales reporting
- Profit analysis
- Purchase and stock management

The system uses Java for application logic, JDBC for database connectivity, and MySQL for data storage.

---

# 2. Objectives

The main objectives of the system are:

- Manage medicines efficiently.
- Maintain supplier information.
- Maintain customer information.
- Generate customer bills.
- Automatically update medicine stock after sales.
- Maintain bill history.
- Search previous bills.
- Monitor low-stock and out-of-stock medicines.
- Track medicines approaching expiry.
- Generate sales reports.
- Identify top-selling medicines.
- Calculate store profit.
- Manage medicine purchases from suppliers.
- Automatically increase stock after purchases.
- Maintain purchase history.

---

# 3. Scope

The system is intended for small and medium-sized medical stores.

The application currently supports:

- Medicine management
- Supplier management
- Customer management
- Billing
- Bill history
- Inventory reports
- Sales reports
- Profit reports
- Purchase management
- Purchase history
- Stock entry
- Supplier purchase tracking

## Version 3.1 Development Scope

Version 3.1 focuses on:

- User Authentication
- Username and Password Validation
- User Session Management
- Logout
- Admin and Cashier Roles
- Role-Based Access Control

Future versions will introduce:

- PDF invoice generation
- Data export
- Database backup and restore
- GUI/Web interface

---

# 4. Functional Requirements

## 4.1 Medicine Management

### FR-MED-01
The system shall allow users to add a new medicine.

### FR-MED-02
The system shall allow users to view all medicines.

### FR-MED-03
The system shall allow users to update medicine information.

### FR-MED-04
The system shall allow users to delete medicines.

### FR-MED-05
The system shall allow users to search medicines by:

- Medicine ID
- Medicine Name
- Category
- Formula
- Batch Number

### FR-MED-06
The system shall maintain medicine stock quantity.

### FR-MED-07
The system shall store medicine expiry and manufacturing dates.

---

# 5. Supplier Management

### FR-SUP-01
The system shall allow users to add suppliers.

### FR-SUP-02
The system shall allow users to view all suppliers.

### FR-SUP-03
The system shall allow users to update supplier information.

### FR-SUP-04
The system shall allow users to delete suppliers.

### FR-SUP-05
The system shall allow users to search suppliers by:

- Supplier ID
- Supplier Name
- Phone Number

---

# 6. Customer Management

### FR-CUS-01
The system shall allow users to add customers.

### FR-CUS-02
The system shall allow users to view all customers.

### FR-CUS-03
The system shall allow users to update customer information.

### FR-CUS-04
The system shall allow users to delete customers.

### FR-CUS-05
The system shall allow users to search customers by:

- Customer ID
- Customer Name
- Phone Number
- Email

---

# 7. Billing Management

### FR-BIL-01
The system shall allow users to create a new bill.

### FR-BIL-02
The system shall allow multiple medicines to be added to a bill.

### FR-BIL-03
The system shall calculate the subtotal for each medicine.

### FR-BIL-04
The system shall calculate the total bill amount.

### FR-BIL-05
The system shall allow discounts to be applied.

### FR-BIL-06
The system shall calculate the final payable amount.

### FR-BIL-07
The system shall automatically reduce medicine stock after successful billing.

### FR-BIL-08
The system shall store bill information in the database.

### FR-BIL-09
The system shall store individual bill items.

---

# 8. Bill History

### FR-HIS-01
The system shall allow users to view previous bills.

### FR-HIS-02
The system shall allow users to search bills using Bill ID.

### FR-HIS-03
The system shall allow users to search previous bills using Customer Name.

### FR-HIS-04
The system shall display purchased medicines associated with a bill.

### FR-HIS-05
The system shall display:

- Bill ID
- Bill Date
- Customer
- User
- Total Amount
- Discount
- Final Amount
- Purchased Medicines

---

# 9. Inventory Reports

### FR-INV-01
The system shall identify medicines with low stock.

### FR-INV-02
The system shall identify medicines that are out of stock.

### FR-INV-03
The system shall identify medicines expiring within the next 30 days.

### FR-INV-04
The system shall display relevant medicine information such as:

- Medicine ID
- Medicine Name
- Quantity
- Rack Number
- Expiry Date

---

# 10. Sales Reports

### FR-SAL-01
The system shall generate a daily sales report.

### FR-SAL-02
The system shall display the total sales amount for the selected day.

### FR-SAL-03
The system shall generate a monthly sales report.

### FR-SAL-04
The system shall display total bills for the selected month.

### FR-SAL-05
The system shall display total sales for the selected month.

### FR-SAL-06
The system shall identify the top-selling medicines.

### FR-SAL-07
The system shall calculate total revenue.

### FR-SAL-08
The system shall calculate total purchase cost.

### FR-SAL-09
The system shall calculate net profit.

---

# 11. Purchase Management — Completed

## 11.1 Purchase Creation

### FR-PUR-01
The system shall allow users to create a new medicine purchase.

### FR-PUR-02
The system shall allow users to select a supplier for a purchase.

### FR-PUR-03
The system shall allow multiple medicines to be added to a purchase.

### FR-PUR-04
The system shall record the quantity purchased for each medicine.

### FR-PUR-05
The system shall record the purchase price of each medicine.

### FR-PUR-06
The system shall calculate the subtotal for each purchased medicine.

### FR-PUR-07
The system shall calculate the total purchase amount.

---

# 12. Stock Entry — Completed

### FR-STK-01
The system shall increase medicine stock after a successful purchase.

### FR-STK-02
The system shall update the existing quantity in the medicines table.

### FR-STK-03
The system shall maintain the relationship between a purchase and its purchased medicines.

### FR-STK-04
The system shall prevent incomplete stock updates if a purchase transaction fails.

---

# 13. Purchase History — Completed

### FR-PH-01
The system shall store completed purchases.

### FR-PH-02
The system shall allow users to view previous purchases.

### FR-PH-03
The system shall display purchase details including:

- Purchase ID
- Supplier
- Purchase Date
- Total Amount

### FR-PH-04
The system shall display individual medicines included in a purchase.

### FR-PH-05
The system shall allow purchase records to be searched by Purchase ID.

---

# 14. Version 3.1 Authentication Requirements

The following requirements are planned for a future version.

### FR-AUTH-01
The system shall provide user login functionality.

### FR-AUTH-02
The system shall authenticate users using username and password.

### FR-AUTH-03
The system shall support different user roles.

Planned roles:

- Admin
- Cashier

### FR-AUTH-04
The system shall restrict access to selected features according to user role.

### FR-AUTH-05
The system shall provide a logout option.

---

# 15. Future Dashboard Requirements

The dashboard is planned for a future version.

The dashboard may display:

- Today's Sales
- Today's Bills
- Total Medicines
- Low Stock Medicines
- Out-of-Stock Medicines
- Expiring Medicines
- Today's Profit

---

# 16. Database Requirements

The system uses MySQL as the relational database.

Current tables include:

- users
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

# 17. Non-Functional Requirements

## 17.1 Performance

The system should return database queries within a reasonable amount of time for a small or medium-sized medical store.

## 17.2 Reliability

The system should prevent inconsistent stock updates during billing and purchasing operations.

## 17.3 Maintainability

The application should follow layered architecture and separate:

- Model
- DAO
- Service
- Menu

components.

## 17.4 Usability

The console interface should provide clear menus, instructions, and error messages.

## 17.5 Security

Database credentials should not be exposed in public repositories.

Future authentication functionality should securely handle user credentials.

---

# 18. Project Status

## Completed Modules

- [x] Medicine Management
- [x] Supplier Management
- [x] Customer Management
- [x] Billing Management
- [x] Bill History
- [x] Inventory Reports
- [x] Sales Reports
- [x] Profit Analysis
- [x] Purchase Management
- [x] Purchase History
- [x] Purchase Details
- [x] Automatic Stock Update
- [x] Purchase Transaction Management

## Current Development

- [ ] User Authentication
- [ ] Login System
- [ ] Admin & Cashier Roles
- [ ] Role-Based Access Control
- [ ] Logout

# 19. Architecture

The application follows a layered architecture:

```text
User
  ↓
Menu
  ↓
Service
  ↓
DAO
  ↓
JDBC
  ↓
MySQL Database