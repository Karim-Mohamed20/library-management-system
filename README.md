# Library Management API

## Overview

A Spring Boot REST API for user authentication, book records and book issue and return workflows. It provides secure, role-based access.

## Features

- User registration, JWT login and role-based access
- Book browsing and catalog updates
- Issue and return tracking with quantity and availability updates
- Swagger UI documentation

## Technology

- Java 17 and Spring Boot
- Spring Security and JWT
- Spring Data JPA and MySQL
- Maven
- Swagger UI

## Setup and run

1. Start the application from the project directory:

   ```
   .\mvnw.cmd spring-boot:run
   ```

2. Open Swagger UI at `http://localhost:8080/swagger-ui.html`. The OpenAPI JSON is available at `http://localhost:8080/v3/api-docs`.

## API routes

| Method | Path | Access |
| --- | --- | --- |
| `POST` | `/auth/registernormaluser` | Public |
| `POST` | `/auth/login` | Public |
| `POST` | `/admin/registeradminuser` | Privileged role |
| `GET` | `/books/getallbooks` | Authenticated |
| `GET` | `/books/getbookbyid/{id}` | Authenticated |
| `POST` | `/books/addbook` | Privileged role |
| `PUT` | `/books/updatebook/{id}` | Privileged role |
| `DELETE` | `/books/deletebook/{id}` | Privileged role |
| `POST` | `/issuerecords/issuethebook/{bookId}` | Authenticated |
| `POST` | `/issuerecords/returnthebook/{issueRecordId}` | Authenticated |
