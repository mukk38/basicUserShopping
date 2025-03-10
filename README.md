# JWT Authentication & Role-Based Access Control API

## Overview
This project implements **JWT-based authentication** for user sessions and provides a secure **REST API** for managing content. The system is backed by **PostgreSQL**, where users, content, purchases, content categories, and comments are stored with proper relationships.

## Features
- **JWT Authentication** for secure user sessions.
- **Role-Based Access Control (RBAC)** to restrict API access based on user roles.
- **RESTful API** for managing users, content, purchases, categories, and comments.
- **PostgreSQL Database** with well-structured relationships.
- **Spring Boot & Spring Security** for authentication and authorization.

## Technologies Used
- **Java 17+**
- **Spring Boot** (Web, Security, Data JPA)
- **PostgreSQL** (for relational data storage)
- **JWT (JSON Web Token)** for authentication
- **Maven** for dependency management

## Database Schema
The system maintains the following main entities:
- **Users**: Stores user credentials, roles, and authentication data.
- **Content**: Represents the available content in the system.
- **Purchases**: Tracks user purchases of content.
- **Content Categories**: Categorizes content items.
- **Comments**: User-generated comments on content.

## Getting Started
### Prerequisites
Ensure you have the following installed:
- Java 17+
- PostgreSQL
- Maven

### Installation & Setup
1. **Clone the repository:**
   ```sh
   git clone <repo-url>
   cd <project-directory>
   ```

2. **Configure PostgreSQL**
   - Create a database in PostgreSQL.
   - Update `application.properties` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Run the application**
   ```sh
   mvn spring-boot:run
   ```

## API Authentication
### User Registration & Login
- **Register a new user**:
  ```http
  POST /api/auth/register
  ```
  Example payload:
  ```json
  {
    "username": "john_doe",
    "password": "securepassword",
    "role": "USER"
  }
  ```

- **Login and get JWT Token**:
  ```http
  POST /api/auth/login
  ```
  Example response:
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI..."
  }
  ```

### Secured API Endpoints
JWT token must be included in the `Authorization` header for accessing secured endpoints:
```
Authorization: Bearer <your_jwt_token>
```

Example protected endpoint:
```http
GET /api/content/all
```

## Role-Based Access Control (RBAC)
- **Admin**: Full access to manage users, content, and purchases.
- **User**: Can view content and make purchases.
- **Guest**: Can browse public content but has limited access.

## Future Improvements
- Implement refresh tokens for better session management.
- Add OAuth2 authentication support.
- Improve logging and monitoring.





