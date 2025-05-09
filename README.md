# Trace-Bullet User Project

This project demonstrates a basic trace-bullet implementation of the User API using Spring Boot. It includes a RESTful API and runs in a containerized environment.

## Prerequisites

- Java 21+
- Docker
- Maven (wrapper included)

## Packaging the Application

Use the Maven wrapper to build the application:

```bash
./mvnw package
```

## Building the Docker Image

Build the Docker image using the following command:

```bash
docker build -t test:1.0 .
```

## Running the Application

To run the container with port `8080` exposed:

```bash
docker run -p 8080:8080 test:1.0
```

## API Endpoints

The application exposes the following REST endpoints:

| Method | Endpoint             | Description              |
|--------|----------------------|--------------------------|
| GET    | `/api/v1/users`      | Retrieve list of users   |
| POST   | `/api/v1/users`      | Create a new user        |
| GET    | `/api/v1/users/{id}` | Retrieve a user by ID    |
| PUT    | `/api/v1/users/{id}` | Update an existing user  |
| DELETE | `/api/v1/users/{id}` | Delete a user by ID      |
| POST   | `/api/v1/importer`   | Import users via multipart cvs file    |

## Database Configuration

The application uses an in-memory [H2](https://www.h2database.com/) database, which is automatically configured and does not require any external setup.

The H2 console is accessible (if enabled) at:  
`http://localhost:8080/h2`

---
