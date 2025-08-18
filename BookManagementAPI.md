# Book Management API

This document describes the implementation of the **Book Management API** project, where we built a RESTful service to manage books, users, and prices.

---

## Overview
The goal of this task was to:
- Design the API using **OpenAPI Specification (`api.yaml`)**.
- Use **OpenAPI Generator** to generate Java interfaces, DTOs, and API stubs.
- Implement the business logic by writing the **delegate classes**.
- Visualize and test the endpoints using **Swagger UI**.

---

## API Design (api.yaml)
- We manually wrote the `api.yaml` file describing:
    - Endpoints
    - Request/response models
    - Paths for **Books**, **Users**, and **Prices**
- This became the **single source of truth** for the API.

---

## Code Generation
- Used **OpenAPI Generator Maven plugin** to generate:
    - API interfaces
    - Models (POJOs)
    - Controllers (stubs)

Example:
- Generated `BooksApi` interface from the spec.
- We then implemented the logic in `BooksApiDelegate`.

---

## Implementation
- Implemented **delegate classes** (e.g., `BooksApiDelegateImpl`, `UsersApiDelegateImpl`, `PricesApiDelegateImpl`).
- Connected delegates to the **service layer** and **repository layer**.
- Handled CRUD operations for Books, Users, and Prices.

### Example (Books)
- `GET /api/books` → implemented in `BooksApiDelegateImpl#getBooks()`
- `POST /api/books` → implemented in `BooksApiDelegateImpl#createBook()`
- Similar delegates were created for **Users** and **Prices**.

---

## Entities
We designed and mapped the following entities in the database:

1. **Book**
    - Fields: `id`, `title`, `author`, `borrowedBy`, `user_id`, `price`, `price_id`, etc.
2. **User**
    - Fields: `id`, `name`, `email`, `address`, `borrowedBooks`, `phone`, etc.
3. **Price**
    - Fields: `id`, `bookId`, `currency`, `amount`, etc.

Relationships:
- One **User** can borrow MULTIPLE **Books**.
- One **Book** can be borrowed by a SINGLE **User** ONLY.

---

## Swagger UI
- It was used to **visualize and test endpoints** once the implementation was done.
- Accessible at:
    - Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## Testing
- Verified endpoints using **Postman** and **Swagger UI**.
- Ensured CRUD operations worked for all entities.

---

## Deliverables
- `api.yaml` (OpenAPI specification).
- Generated Java sources from `api.yaml`.
- Implemented delegate classes for API logic.
- Functional CRUD endpoints for Books, Users, and Prices.
