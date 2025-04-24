# User Management Service

A Spring Boot application that provides REST APIs to manage user data with full-text search capabilities. The application loads user data from an external API into an in-memory H2 database and provides various endpoints to search and retrieve user information.

## Features

- Load user data from external API (https://dummyjson.com/users)
- In-memory H2 database for data storage
- Full-text search capabilities using Hibernate Search
- RESTful APIs for user management
- Swagger/OpenAPI documentation
- Comprehensive error handling
- Unit tests with JUnit and Mockito
- Code coverage reporting with JaCoCo

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Building the Application

To build the application, run:

```bash
mvn clean install
```

## Running the Application

To run the application, use:

```bash
mvn spring-boot:run
```

The application will start on port 8080.

## API Documentation

Once the application is running, you can access the Swagger UI at:
http://localhost:8080/swagger-ui.html

## H2 Console

The H2 console is available at:
http://localhost:8080/h2-console

Connection details:
- JDBC URL: jdbc:h2:mem:userdb
- Username: sa
- Password: password

## Available Endpoints

1. Load Users from External API
   ```
   POST /api/users/load
   ```

2. Search Users
   ```
   GET /api/users/search?searchTerm={term}
   ```

3. Find User by ID
   ```
   GET /api/users/{id}
   ```

4. Find User by Email
   ```
   GET /api/users/email/{email}
   ```

5. Get All Users
   ```
   GET /api/users
   ```

## Configuration

The application can be configured through `application.yml`. Key configuration properties include:

- External API settings
- Connection timeouts
- Retry parameters
- Database configuration

## Testing

To run the tests:

```bash
mvn test
```

To generate a code coverage report:

```bash
mvn verify
```

The coverage report will be available in `target/site/jacoco/index.html`

## Error Handling

The application includes comprehensive error handling for:
- External API failures
- Invalid requests
- Resource not found
- Unexpected errors

## Logging

Logging is configured to provide:
- INFO level for general application events
- DEBUG level for detailed application flow
- ERROR level for error conditions

Logs can be found in the application output and can be configured in `application.yml`. 