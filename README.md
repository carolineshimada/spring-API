# Restaurant Delivery API

## Overview

This project is a RESTful API designed for a restaurant delivery service. It enables the management of orders, customers, menus, and deliveries, facilitating seamless integration with frontend applications and other services. The API is built using Spring Boot and SQL Server for robust performance and scalability.

## Technologies Used

- **Spring Boot**: A powerful framework to build production-ready applications with ease.
- **SQL Server**: A reliable and scalable relational database management system.

## RESTful Strategy

The API adheres to RESTful principles, ensuring stateless operations, uniform interfaces, and resource-based interactions. This is achieved through the following strategies:

- **Resource-Oriented Architecture**: The API exposes endpoints corresponding to various entities like orders, customers, menus, and deliveries.
- **HTTP Methods**: Utilizes standard HTTP methods (GET, POST, PUT, DELETE) to perform CRUD operations on resources.
- **Statelessness**: Each request from a client to the server must contain all the information the server needs to fulfill that request.
- **GlobalExceptionHandler**: A centralized error handling mechanism to ensure consistent and meaningful responses for any exceptions that occur during API operations. This handler follows RESTful principles by returning appropriate HTTP status codes and messages.

## Project Structure

The project is organized into the following packages, each with a specific responsibility:

### 1. Controllers
This package contains the REST controllers that handle HTTP requests and responses. Each controller corresponds to a specific entity and contains endpoints for CRUD operations.

- **Example**: `OrderController`, `OrderItemController`, `CustomerController`, `ProductController`

### 2. Entities
This package defines the entity classes that map to the database tables. These classes represent the data model and include fields, getters, setters, and relationships.

- **Example**: `Order`, `OrderItem`, `Customer`, `Product`

### 3. Repositories
This package includes repository interfaces that extend `JpaRepository` or `CrudRepository`. These interfaces provide CRUD operations and custom queries for interacting with the database.

- **Example**: `OrderRepository`, `OrderItemRepository`, `CustomerRepository`, `ProductRepository`

### 4. Exceptions
This package contains custom exception classes and the `GlobalExceptionHandler`. The exception handler ensures that all exceptions are caught and handled in a uniform manner, providing meaningful error responses to clients.

## GlobalExceptionHandler

The `GlobalExceptionHandler` is a critical component of this API, ensuring that all exceptions are handled gracefully and that clients receive consistent and informative error responses. It intercepts exceptions thrown by the application and returns appropriate HTTP status codes along with error messages. This approach helps maintain the stateless nature of the API and provides a better user experience.


## Conclusion

This API project is in progress, and is being designed to provide a robust and scalable solution for restaurant delivery services. By adhering to RESTful principles and utilizing Spring Boot and SQL Server, the project ensures efficient and reliable performance. The structured package organization and the `GlobalExceptionHandler` contribute to a maintainable and user-friendly API.

## Demo

https://github.com/carolineshimada/spring-API/assets/71412867/6a399371-312c-4184-b0dc-1555e5c6cbc2


