# ITS-1114-AAD-Task1-OrderManage

A backend REST API built with Spring Boot for an **Order Management System** as part of the Advanced API Development module. The system handles specific features segregated across three user roles: **Admin**, **Cashier**, and **Customer**.

---

## 🛠️ Tech Stack & Requirements

* **Java Version:** 21
* **Framework:** Spring Boot 4.0.6 (with Web MVC & Spring Data JPA)
* **Database:** MySQL (Version 8.0.x)
* **Build Tool:** Maven
* **Dependency Management:** Lombok (for boilerplate optimization)

---

## 📋 System Requirements & Features

The API provides tailored endpoints based on the following functional breakdown:

### 👑 Admin Features
* **Employee Management:**
    * Save new employees
    * Retrieve employee details
    * Edit existing employee records
* **Customer Management:**
    * Save new customers
    * Retrieve customer details
    * Edit existing customer records

### 💵 Cashier Features
* **Order Tracking:** View all system orders with dynamic filtering by **Customer Name**.
* **Inventory Visibility:** View all store items with dynamic filtering by **Item Name**.
* **Sales Operations:** Process and place new customer orders.

### 👤 Customer Features
* **Order History:** Review a list of all orders specific to the authenticated/selected customer.

---

## ⚙️ Configuration Details

The application uses the following pre-configured parameters inside `src/main/resources/application.properties`:

```properties
spring.application.name=task1
spring.datasource.url=jdbc:mysql://localhost:3306/aad_task1?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=mysql
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
