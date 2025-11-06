# 🧩 Task Manager API (Spring Boot + JWT + MySQL) :-

A secure **Task Manager REST API** built with **Spring Boot**, **Spring Security (JWT Authentication)**, and **MySQL**.
It allows users to register, log in, and perform **CRUD operations** on tasks, each linked to their account.

--------------------------------------------------

## 🚀 Features :-

* User Registration & Login (JWT-based Authentication)
* Password Encryption with BCrypt
* Protected Task Endpoints
* CRUD Operations for Tasks
* Stateless Authentication
* MySQL Database Integration
* Clean Layered Architecture (Controller → Service → Repository)

--------------------------------------------------

## 🧱 Tech Stack :-

| Component  | Technology                  |
| ---------- | --------------------------- |
| Language   | Java 17+                    |
| Framework  | Spring Boot 3.x             |
| Security   | Spring Security (JWT)       |
| Database   | MySQL                       |
| ORM        | Spring Data JPA / Hibernate |
| Build Tool | Maven                       |

--------------------------------------------------

## 📂 Project Structure :-

src/main/java/com/example/
│
├── config/
│   ├── JwtAuthenticationEntryPoint.java
│   └── SecurityConfig.java
│
├── controllers/
│   ├── AuthController.java
│   └── TaskController.java
│
├── dto/
│   ├── AuthRequest.java
│   ├── AuthResponse.java
│   └── RegisterRequest.java
│
├── models/
│   ├── Task.java
│   └── User.java
│
├── repositories/
│   ├── TaskRepository.java
│   └── UserRepository.java
│
├── security/
│   ├── JwtFilter.java
│   └── JwtUtil.java
│
└── services/
    ├── TaskService.java
    └── UserService.java


-----------------------------------------------

## ⚙️ Setup Instructions :-

### 1️⃣ Prerequisites :-

* Install **Java 17+**
* Install **Maven**
* Install and start **MySQL**
* Use an IDE such as **IntelliJ IDEA** or **Spring Tool Suite**

-----------------------------------------------

### 3️⃣ Configure Database :-

Create a new database in MySQL:

```sql
CREATE DATABASE task_manager;
```

Update your **`application.properties`**:-

spring.datasource.url=jdbc:mysql://localhost:3306/task_manager?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

app.jwtSecret=ReplaceThisWithAStrongSecretKey12345
app.jwtExpirationMs=86400000
server.port=8080

-----------------------------------------------

### 4️⃣ Build & Run

Use Maven:

Or directly from your IDE, run:
`TaskManagerApiApplication.java`

--------------------------------------------------

## 🧪 API Endpoints :-

### 🔐 Authentication

| Method | Endpoint             | Description             |
| ------ | -------------------- | ----------------------- |
| `POST` | `/api/auth/register` | Register a new user     |
| `POST` | `/api/auth/login`    | Login and get JWT token |

**Example:**

```json
POST /api/auth/register
{
  "username": "vinay",
  "password": "1234"
}

-------------------------------------------------------

### 📝 Tasks (Authenticated routes) :-

Add `Authorization: Bearer <token>` header.

| Method   | Endpoint          | Description        |
| -------- | ----------------- | ------------------ |
| `GET`    | `/api/tasks/`     | Get all user tasks |
| `GET`    | `/api/tasks/{id}` | Get a single task  |
| `POST`   | `/api/tasks`      | Create a new task  |
| `PUT`    | `/api/tasks/{id}` | Update a task      |
| `DELETE` | `/api/tasks/{id}` | Delete a task      |

--------------------------------------------------------

### 🔑 Example Login Response :-

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

Use this token in Postman under:

```
Authorization → Type: Bearer Token
```

---------------------------------------------

## 🧰 Common Issues :-

| Problem              | Solution                                          |
| -------------------- | ------------------------------------------------- |
| `401 Unauthorized`   | Ensure valid Bearer token is passed in the header |
| `Database not found` | Check your MySQL URL and credentials              |
| `Invalid JWT`        | Token may have expired or been modified           |

---------------------------------------------------------

## 📘 Swagger Integration (Optional) :-

Add this dependency in `pom.xml` to enable Swagger UI:

<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>

Access Swagger UI after running :-
👉 `http://localhost:8080/swagger-ui.html`

------------------------------------------------------

## 🧑‍💻 Author :-

**Vinay Singh**
Spring Boot Developer | Java | REST APIs

-------------------------------------------------------

