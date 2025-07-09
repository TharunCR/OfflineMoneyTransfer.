# Offline Money Transfer App

This is a Spring Boot-based web application for transferring money between accounts in both **online** and **offline** modes. It uses Thymeleaf for the frontend and MySQL for the database.

---

## Features

Toggle between **Online** and **Offline** modes  
Dashboard showing **Savings** and **Ledger** balances  
Simple **Send Money** form (amount, receiver ID, PIN)  
Auto-sync ledger and savings balances  
MySQL-based persistent data  

---

## Tech Stack

- Java 17
- Spring Boot 3.x
- Thymeleaf (HTML templating)
- MySQL
- Maven

---

## How It Works

- The application loads the user's savings and ledger balances.
- You can toggle between **online** and **offline** mode.
- In offline mode, transactions are stored in a local ledger (not synced).
- When toggled to online, offline transactions are automatically synced.
- Dashboard updates in real time after each action.

---

## Folder Structure

offline-transfer/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── offline_transfer/
│   │   │               ├── controller/
│   │   │               ├── model/
│   │   │               ├── repository/
│   │   │               ├── service/
│   │   │               └── OfflineTransferAppApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   └── css/
│   │       │       └── styles.css
│   │       └── templates/
│   │           └── dashboard.html
│   └── test/

