# 🚗 Garage Billing System

The **Garage Billing System** is a simple Java application designed to manage customer service billing for an automotive garage. It uses **Java**, **JDBC**, and **MySQL** to handle basic operations like customer management, service listing, billing, and invoice generation.


## 📦 Features

- Add and manage **Customers**
- View and manage **Services**
- Create and store **Bills**
- Add multiple **Bill Items** under each bill
- Retrieve **Invoice** for a customer’s service
- Clean **DAO-based architecture**


## 🧰 Tech Stack

- **Java** (Core Java, JDBC)
- **MySQL** (as the database)
- **CLI-based** user interface


## 📁 Project Structure
Garage_Billing_System/
├── Main.java
├── dao/
│ ├── CustomerDAO.java
│ ├── ServiceDAO.java
│ ├── BillDAO.java
├── entity/
│ ├── Customer.java
│ ├── Service.java
│ ├── Bill.java
│ └── BillItem.java
└── DBConnection.java
