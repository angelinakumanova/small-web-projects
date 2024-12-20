

# 👨🏻‍⚕️ Doctorly 
Doctorly is a **web-based application** designed for patients to **register**, **log in**, and conveniently **schedule appointments with doctors.** Built with modern technologies, it emphasizes **secure authentication**, **intuitive user experience**, and **efficient appointment management.**

<div align="center">
    <p align="center"><i>Quick overview</i></p>
    <img src="/readme-images/Doctorly/doctorly-overview.gif" alt="GIF Overview" width="600px" ">
</div>

<br>

---

## About the Project
Inspired by completing a **Spring Data** course, **Doctorly** was developed to **apply newly learned concepts** and **explore Spring Security and Web basics.** It aims to simplify the process of booking appointments, providing an intuitive and secure platform for users.

<br>

---

## Features
- **Patient Registration & Login:** Secure authentication for new and existing users.
- **Effortless Appointment Booking:** Easily register, log in, and manage appointments with doctors.
- **Secure Authentication & Authorization:** Secure login and access management using Spring Security.
- **Form Validation:** Ensuring accurate data entry with real-time validation for a seamless user experience.
- **Data Management:** Reliable database storage using MySQL, with secure password hashing for user accounts.

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
![home-page](/readme-images/Doctorly/1-home-page.jpg)
#### 2. Doctors Search
![doctors-search](/readme-images/Doctorly/2-search-doctors.jpg)
#### 3. Login Page
![login-page](/readme-images/Doctorly/3-login.jpg)
#### 4. Registration Page
![registration-page](/readme-images/Doctorly/4-register.jpg)
#### 5. My Appointments Page
![appointments-page](/readme-images/Doctorly/5--my-appointments.jpg)


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

### 3) Build the Application
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
