# Spring Security Integration

This document describes the implementation of **basic authentication and authorization** in the Book Management API using Spring Security.

---

## Overview
The goal of this task was to:
- Secure the Book Management API endpoints.
- Use **Basic Authentication** with Spring Security.
- Store users and roles in the database (Admins table).
- Enforce role-based access control (RBAC) for different endpoints.

---

## SecurityConfig
- Defined a `SecurityConfig` class using `SecurityFilterChain`.
- Configured role-based access:
    - **Books**
        - `GET /api/books/**` → accessible to all
        - `POST/PUT/DELETE /api/books/**` → only accessible to `ADMIN`
    - **Users**
        - `GET /api/users/**` → accessible to all
        - `POST /api/users/**` → accessible to all (registration use case)
        - `PUT/DELETE /api/users/**` → only accessible to `ADMIN`
    - **Prices**
        - `GET /api/prices/**` → accessible to all
        - `POST/PUT/DELETE /api/prices/**` → only accessible to `ADMIN`

- Enabled **httpBasic()** for authentication.
- Configured `BCryptPasswordEncoder` for password hashing.

---

## CustomUserDetailsService
- Implemented a custom service that loads user details from the database.
- Integrated with `AdminRepository` to fetch users by username.
- Returned Spring Security `User` objects with:
    - Username
    - Hashed password
    - Roles (e.g., `USER`, `ADMIN`)

---

## Password Generator
- Implemented a helper class to generate **BCrypt hashed passwords**.
- Used to insert secure passwords into the database for testing.

---

## Authentication & Authorization Flow
1. User calls an API with **Basic Auth** (`username:password`).
2. Spring Security intercepts the request.
3. Authentication:
    - Credentials are decoded and verified against DB (via `CustomUserDetailsService`).
    - BCrypt is used to match hashed passwords.
4. Authorization:
    - Endpoint access is checked against user’s roles.
    - Example: Only `ADMIN` can delete users or books.
5. If valid, request proceeds to the controller and returns a response.

---

## Testing
- Inserted sample users in the `admins` table with roles `ADMIN` and `USER`.
- Verified:
    - Non-admin users cannot delete or modify resources.
    - Admin users can perform full CRUD operations.
    - Unauthorized users (no credentials) receive `401 Unauthorized`.

---

## Deliverables
- Fully integrated **Spring Security** setup with Basic Auth.
- Role-based access control for all endpoints.
- BCrypt password encryption for secure authentication.
