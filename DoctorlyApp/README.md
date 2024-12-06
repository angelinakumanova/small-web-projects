# 👨🏻‍⚕️ Doctorly 
A **secure** and **user-friendly** website where patients can register or log in to **schedule** and **save appointments with doctors.** Doctorly simplifies appointment management while offering a seamless experience.

<br>

---

## About the Project
Inspired by completing a **Spring Data** course, **Doctorly** was developed to apply newly learned concepts and explore **Spring Security and Web basics.** It aims to simplify the process of booking appointments, providing an intuitive and secure platform for users.

<br>

---

## Features
- **Patient Registration & Login:** Secure authentication for new and existing users.
- **Appointment Scheduling:** Easily schedule and save doctor appointments.
- **Secure Authentication and Authorization:** Ensures user data is protected with Spring Security.
- **Data Management:** Reliable database storage using MySQL.

<br>

---

## Technologies Used
- ### Front-End
    - **HTML & CSS** (including some **Bootstrap** styling)
    - **JavaScript**
- ### Backend
    - **Java**
    - **Spring Boot:**
      - Spring Web
      - Spring Security
      - Spring Data JPA
    - **Thymeleaf:** For server-side rendering
    - **MySQL:** Relational database

<br>

---

## Overview
### Here's what you can expect from Doctorly:

#### 1. Home Page
#### 2. Registration Page
#### 3. Login Page
#### 4. Schedule Appointment


<br>

---

## Setup Instructions

### 1) Clone the Repository
  ``` bash
  git clone https://github.com/angelinakumanova/small-web-projects.git
  cd small-web-projects/DoctorlyApp
  ```

### 2) Set Up the Database
  Configure database connection details in application.properties:
  ```bash
  spring.datasource.username=your-username
  spring.datasource.password=your-password
  ```

### 3)Build the Application
```bash
mvn clean install
```


### 4) Run the Application
  ```bash
  mvn spring-boot:run
```

### 5) Access the Application
  Open a browser and navigate to http://localhost:8080.

***Note: The database is pre-seeded at application startup, allowing you to use existing users whose details are provided in /resources/files/users.json. Alternatively, you can create your own account if desired.***