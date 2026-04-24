# 🚀 Travel & Expense Management System (Spring Boot + JWT)

A backend application built using Spring Boot that allows employees to raise travel requests and managers to approve or reject them.

This project demonstrates real-world backend development concepts like JWT authentication, role-based access, DTO usage, and exception handling.

---

## 🧩 Features

- 🔐 User Authentication using JWT
- 👨‍💻 Employee can:
  - Create travel requests
  - View their own requests
- 👨‍💼 Manager can:
  - View all requests
  - Approve / Reject requests
- 📦 Clean API responses using DTO
- 🚫 Sensitive data protection using `@JsonIgnore`
- 🌍 Global Exception Handling

---

## 🏗️ Tech Stack

- ☕ Java 21
- 🌱 Spring Boot 4
- 🔐 Spring Security + JWT
- 🗄️ PostgreSQL
- 📦 Maven
- 🔄 REST APIs

---

## 📁 Project Structure
com.travel.expense
│
├── controller → REST Controllers
├── service → Business logic
├── service.impl → Implementation layer
├── repository → JPA repositories
├── entity → Database entities
├── dto → Data Transfer Objects
├── security → JWT & Spring Security config
├── exception → Global exception handling

---

## 🔑 API Endpoints

### 🔐 Authentication
POST /api/auth/register
POST /api/auth/login

---

### 👨‍💻 Employee APIs

POST /api/travel/create
GET /api/travel/my-requests

---

### 👨‍💼 Manager APIs

GET /api/travel/all
PUT /api/travel/approve/{id}
PUT /api/travel/reject/{id}

---

## 🔐 JWT Authentication Flow

1. Login using credentials  
2. Receive JWT token  
3. Use token in header:

Authorization: Bearer <your_token>

---

## 🧪 Sample Request

### Create Travel Request

```json
{
  "destination": "Delhi",
  "travelDate": "2026-05-10",
  "returnDate": "2026-05-12",
  "purpose": "Office Work"
}
## 🚀 Future Improvements

- 🌐 Develop a responsive frontend using React.js
- 🔗 Integrate frontend with backend APIs for complete full-stack experience
- ☁️ Deploy application on cloud platforms like AWS.
- 🐳 Containerize application using Docker
- 📊 Add Admin Dashboard for system monitoring
- 📧 Email notifications for request approval/rejection
- 📎 Upload travel bills & attachments
- 🔐 Implement full role-based access control (RBAC)
- 📈 Add pagination, filtering & search for requests
- 🧪 Write unit & integration test cases (JUnit, Mockito)
- 📉 Logging & monitoring using tools like ELK stack







