# ParkingPG - Smart Parking Management System

ParkingPG is a full-stack parking management application built with Spring Boot and React. The system provides secure user authentication, email verification, role-based access control, and parking service management.

## Features

### Authentication & Security
- User registration
- User login
- JWT authentication
- Password encryption with Spring Security
- Role-based authorization
- Secure session management

### Email Verification
- Email verification codes
- SendGrid integration
- Verification code expiration
- Two-step authentication flow
- Email confirmation support

### User Management
- User registration
- User authentication
- User preferences management
- Profile management
- Role assignment

### Security Features
- Spring Security integration
- JWT token generation
- Authentication Manager
- Password hashing
- Protected endpoints

---

## Technologies

### Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- MySQL

### Third-Party Services
- SendGrid
- Java Mail Sender

### Frontend
- React
- JavaScript
- HTML
- CSS

### Tools
- Postman
- Git
- GitHub

---

## Architecture

The application follows a layered architecture:

- Controller Layer
- Service Layer
- Repository Layer
- Security Layer
- Database Layer

Authentication and authorization are handled through Spring Security and JWT tokens.

---

## Core Functionalities

### Authentication Module

- User registration
- User login
- Password encryption
- JWT token generation
- Authentication validation

### Email Verification Module

- Generate verification codes
- Send verification emails
- Verification expiration control
- Two-factor verification flow

### User Module

- Create users
- Manage user preferences
- Role assignment
- Profile management

### Authorization Module

- User roles
- Permission management
- Protected resources
- Access control

---

## Database Design

### User
- id
- firstName
- lastName
- username
- email
- password

### Role
- id
- name

### UserOption
- id
- emailVerification

### EmailVerification
- id
- email
- verificationCode
- expirationDate
- verified

---

## Implemented Concepts

- JWT Authentication
- Spring Security
- Role-Based Access Control (RBAC)
- Password Encryption
- Email Verification
- Two-Step Authentication
- Dependency Injection
- Layered Architecture
- One-to-One Relationships
- Many-to-Many Relationships
- Exception Handling

---

## Security Highlights

- BCrypt password hashing
- JWT token authorization
- Verification code expiration
- Protected endpoints
- Role-based permissions
- Authentication validation

---

## Future Improvements

- Parking reservation system
- Parking zone management
- Real-time parking availability
- QR code parking access
- Payment integration
- AWS deployment
- Docker containerization
- Mobile application support

---

## Author

**Rajan Dz**
