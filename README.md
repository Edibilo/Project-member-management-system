# Project Member Management System

A web-based **Project Member Management System** built with **Spring Boot, Spring Security, Spring Data JPA, Thymeleaf, and MySQL**.

The system allows authenticated users to create and manage projects, add registered users as project members, and control access based on system roles, project ownership, and project membership.

##Images
<img width="1427" height="722" alt="Screenshot 2026-09-18 075255" src="https://github.com/user-attachments/assets/0c205591-12ef-47ec-93fa-fa5d254872aa" />
<img width="1528" height="992" alt="Screenshot 2026-09-18 075213" src="https://github.com/user-attachments/assets/99d65119-2d04-4da9-97b4-71f706e1d963" />
<img width="1427" height="892" alt="Screenshot 2026-09-18 075104" src="https://github.com/user-attachments/assets/a1b7a155-2178-4ae7-a98f-078ea41030dc" />


## 🚀 Features

### Authentication & Users

* User registration
* User login and authentication
* Password encryption
* User profile management
* System role management
* Admin and normal-user access control
* User information retrieval

### Project Management

* Create projects
* View projects owned by the authenticated user
* View projects where the user is a member
* View individual project details
* Update projects
* Delete projects
* Automatically assign the authenticated user as project owner

### Project Members

* Add registered users to projects
* View project members
* Remove project members
* Prevent duplicate project memberships
* Restrict member management to project owners

### Authorization

The system separates **system roles** from **project ownership and membership**.

| User Type          | Permissions                                  |
| ------------------ | -------------------------------------------- |
| Administrator      | Manage system users and roles                |
| Project Owner      | Update/delete projects and manage members    |
| Project Member     | Access projects they belong to               |
| Authenticated User | Create projects and manage their own profile |

A project owner does not need to be a system administrator.

## 🛠️ Technologies

* **Java**
* **Spring Boot**
* **Spring MVC**
* **Spring Security**
* **Spring Data JPA**
* **Hibernate**
* **Thymeleaf**
* **MySQL**
* **Maven**
* **HTML/CSS**

## 🏗️ Project Architecture

```text
src/main/java/iki/fordow
│
├── controller
│   ├── AuthController
│   ├── UserController
│   ├── RoleController
│   ├── ProjectController
│   └── ProjectMemberController
│
├── service
│   ├── AuthService
│   ├── UserService
│   ├── RoleService
│   ├── ProjectService
│   └── ProjectMemberService
│
├── repository
│   ├── UserRepository
│   ├── RoleRepository
│   ├── ProjectRepository
│   └── ProjectMemberRepository
│
├── entity
│   ├── User
│   ├── Role
│   ├── Project
│   └── ProjectMember
│
├── dto
│   ├── UserDto
│   ├── RoleDto
│   ├── ProjectDto
│   └── ProjectMemberDto
│
├── mapper
├── enumerator
└── config
```

## 🗄️ Database Design

The main entities are:

```text
Role
  │
  └── User
        │
        ├── Project (Owner)
        │
        └── ProjectMember
                  │
                  └── Project
```

### Main Tables

#### `roles`

```text
id
name
```

#### `users`

```text
id
username
email
password
status
role_id
```

#### `projects`

```text
id
name
description
start_date
end_date
owner_id
```

#### `project_member`

```text
id
project_id
member_id
joined_at
```

A unique constraint on `project_id` and `member_id` prevents the same user from being added to the same project more than once.

## 🔐 Security

The application uses **Spring Security** to protect authenticated resources.

### Public

```text
GET  /api/v1/auth/register
POST /api/v1/auth/register
GET  /api/v1/auth/login
```

### Users

```text
GET /api/v1/users
GET /api/v1/users/profile
GET /api/v1/users/profile/edit
GET /api/v1/users/{userId}/edit
```

### Projects

```text
GET  /api/v1/projects
POST /api/v1/projects

GET  /api/v1/projects/{projectId}
GET  /api/v1/projects/{projectId}/edit
GET  /api/v1/projects/{projectId}/delete
```

### Project Members

```text
GET  /api/v1/projects/{projectId}/members
POST /api/v1/projects/{projectId}/add-member

GET /api/v1/project-member/{memberId}/{projectId}/delete
```

## ⚙️ Requirements

Before running the application, install:

* Java
* Maven
* MySQL Server
* Git
* IntelliJ IDEA, VS Code, or another Java IDE

## 🗃️ Database Setup

Create the database:

```sql
CREATE DATABASE fordow;
```

The application is configured for MySQL on port `3307`.

```properties
spring.application.name=fordow

spring.datasource.url=jdbc:mysql://localhost:3307/fordow
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Update the database username and password according to your environment.

## ▶️ Running the Application

### Clone the repository

```bash
git clone https://github.com/YOUR-USERNAME/fordow.git
```

### Enter the project

```bash
cd fordow
```

### Create the database

```sql
CREATE DATABASE fordow;
```

### Run the application

```bash
mvn spring-boot:run
```

Or run the main Spring Boot application class from IntelliJ IDEA.

## 🌐 Application URLs

After starting the application:

```text
http://localhost:8080
```

Registration:

```text
http://localhost:8080/api/v1/auth/register
```

Login:

```text
http://localhost:8080/api/v1/auth/login
```

## 🔄 Application Workflow

```text
1. Register User
       ↓
2. Login
       ↓
3. Access Profile
       ↓
4. Create Project
       ↓
5. User becomes Project Owner
       ↓
6. View Project
       ↓
7. Add Registered Users
       ↓
8. Members Access Project
       ↓
9. Owner Updates / Deletes Project
       ↓
10. Owner Removes Members
```

## 👤 Roles and Project Ownership

System roles and project ownership are separate concepts.

### System Roles

```text
ROLE_ADMIN
ROLE_USER
```

System roles control application-wide permissions.

### Project Ownership

A normal user can own a project:

```text
User
ROLE_USER
   │
   └── Project
        │
        ├── Owner
        ├── Member
        └── Member
```

Project management permissions are based on ownership and membership rather than requiring every project owner to be an administrator.

## 🔒 Password Security

Passwords are encoded using Spring Security's `PasswordEncoder` before being stored in the database.

```java
passwordEncoder.encode(user.getPassword());
```

Plain-text passwords should never be stored in the database.

## 🧩 Design Principles

* Layered architecture
* Separation of concerns
* DTO-based data transfer
* Repository pattern
* Service-layer business logic
* Authentication and authorization
* JPA entity relationships
* Explicit project ownership
* Join entity for project membership
* Database constraints for data integrity
* Input validation

## 📈 Future Improvements

* REST API with JSON responses
* JWT authentication
* Role-based method security
* Global exception handling
* Pagination
* Project search
* Project status
* Task management
* Task assignment
* Project activity history
* Notifications
* File attachments
* Dashboard statistics
* Swagger/OpenAPI documentation
* Unit and integration tests
* Docker deployment
* CI/CD pipeline

## 🎯 Learning Goals

This project demonstrates practical experience with:

* Java and Spring Boot
* Spring MVC
* Spring Security
* Authentication and authorization
* Hibernate/JPA
* MySQL
* Database relationships
* DTOs and mappers
* Service-layer business logic
* Thymeleaf
* Git and GitHub
* CRUD application development

## 👨‍💻 Author

**Mohamed**

GitHub: https://github.com/Edibilo/Project-member-management-system

---

⭐ If you find this project useful, consider giving the repository a star.
