# Bus Ticket Generating System

## Overview

The Bus Ticket Generating System is a Java Swing-based desktop application developed to automate bus ticket booking, fare calculation, ticket generation, and ticket management operations. The system provides separate modules for Conductors and Administrators, ensuring efficient ticket handling and monitoring of daily transport operations.

The application uses Java Swing for the graphical user interface and MySQL as the backend database for storing user credentials, ticket records, and billing information.

---

## Features

### Conductor Module

* Secure conductor login authentication.
* Select boarding and destination stops.
* Automatic calculation of number of stops.
* Automatic fare calculation based on travel distance.
* Support for multiple passengers.
* Automatic ticket number generation.
* Ticket preview before saving.
* Save ticket details to the database.
* View daily passenger statistics.

### Admin Module

* Secure admin login authentication.
* Search tickets by date.
* View complete ticket details.
* Track daily ticket sales.
* Calculate total daily revenue collection.
* Analyze passenger counts from different boarding locations.

### Database Management

* Stores staff login credentials.
* Stores generated ticket information.
* Maintains bill number records.
* Supports reporting and analysis.

---

## Technologies Used

### Frontend

* Java Swing

### Backend

* Java

### Database

* MySQL

### Connectivity

* JDBC (Java Database Connectivity)

---

## Project Structure

```text
Bus Ticket Generating System
│
├── FirstFrame.java
├── ConductorFrame.java
├── AdminFrame.java
├── MyFrame.java
├── ThirdFrame.java
├── Frame.java
├── AdminNextFrame.java
└── Database (MySQL)
```

---

## Application Workflow

```text
Start Application
        │
        ▼
    FirstFrame
        │
 ┌──────┴──────┐
 │             │
 ▼             ▼
Conductor    Admin
 Login       Login
 │             │
 ▼             ▼
MyFrame   AdminNextFrame
 │
 ▼
Generate Ticket
 │
 ▼
ThirdFrame
 │
 ▼
Save Ticket
 │
 ▼
MySQL Database
```

---

## Database Tables

### staff

Stores login credentials and user roles.

| Column   | Description        |
| -------- | ------------------ |
| userno   | User ID            |
| password | User Password      |
| role     | Admin or Conductor |

### ticket

Stores generated ticket information.

| Column      | Description       |
| ----------- | ----------------- |
| ticket      | Ticket Number     |
| dateTime    | Date and Time     |
| from        | Boarding Stop     |
| to          | Destination Stop  |
| np          | Number of Persons |
| ns          | Number of Stops   |
| amount      | Ticket Fare       |
| TotalAmount | Total Fare        |

### ticket_bills

Stores bill numbers.

| Column      | Description    |
| ----------- | -------------- |
| bill_number | Bill Number    |
| currentDate | Date Generated |

---

## Fare Calculation Logic

| Number of Stops   | Fare (₹)                   |
| ----------------- | -------------------------- |
| 1 – 3 Stops       | 8                          |
| 4 – 6 Stops       | 12                         |
| More than 6 Stops | Additional charges applied |

Total Fare:

Total Fare = Ticket Price × Number of Passengers




## Screens

1. Home Page
2. Conductor Login
3. Admin Login
4. Ticket Generation Page
5. Ticket Preview Page
6. Daily Passenger Statistics Page
7. Admin Report Page

---

## Prerequisites

Before running the project, install:

* Java JDK 8 or above
* MySQL Server
* Eclipse IDE (recommended)
* MySQL JDBC Connector

---

## Database Configuration

Update the database credentials in the Java files if required:


Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/bus",
    "root",
    "your_password"
);


---

## How to Run

1. Clone the repository.


git clone https://github.com/your-username/bus-ticket-generating-system.git


2. Import the project into Eclipse.

3. Create the MySQL database and required tables.

4. Configure JDBC Connector.

5. Update database credentials.

6. Run:

FirstFrame.java

7. Login as Admin or Conductor and use the system.

## Future Enhancements

* Online ticket booking support.
* QR Code-based tickets.
* PDF ticket generation.
* Passenger database management.
* Route management module.
* Revenue analytics dashboard.
* Cloud database integration.
* Mobile application support.


## Author

Developed as a Java Swing and MySQL based Bus Ticket Generating System project for learning database connectivity, GUI development, and ticket management automation.


⭐ If you found this project useful, consider giving it a star on GitHub.
