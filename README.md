# NovaBank User Account Service

**NovaBank User Account Service** is a Spring Boot microservice designed to manage user account operations within the NovaBank microservices architecture.

The service uses Spring Security and JWT for authentication and authorization, PostgreSQL for data persistence, Eureka for service discovery, and Apache Kafka for asynchronous messaging.

## Technologies Used

* **Java 21**
* **Spring Boot 4.1.2**
* **Spring Security**
* **Spring Data JPA**
* **Spring Cloud Netflix Eureka Client**
* **Spring Boot Actuator**
* **PostgreSQL**
* **Apache Kafka**
* **JSON Web Token (JWT)**
* **Maven**

## Architecture

The User Account Service is part of the NovaBank microservices ecosystem.

It integrates with:

* **Eureka Server:** Registers the service and enables service discovery.
* **PostgreSQL:** Stores user account information.
* **Apache Kafka:** Supports asynchronous communication through event production.
* **Spring Security:** Handles application security.
* **JWT:** Provides token-based authentication.

## Project Configuration

| Configuration    | Value                         |
| ---------------- | ----------------------------- |
| Application Name | novabank-user-account-service |
| Server Port      | 8081                          |
| Database         | PostgreSQL                    |
| Database Name    | novabank_user_account         |
| Eureka Server    | localhost:8761                |
| Kafka Server     | localhost:9092                |
| Java Version     | 21                            |

## Prerequisites

Before running this application, ensure you have installed:

1. Java Development Kit (JDK) 21.
2. Maven.
3. PostgreSQL.
4. Apache Kafka.
5. Eureka Discovery Server.

## Database Configuration

Create a PostgreSQL database:

```sql
CREATE DATABASE novabank_user_account;
```

Configure your database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/novabank_user_account
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.show_sql=true
```

## JWT Configuration

The application uses JWT for authentication.

Configure the JWT properties:

```properties
app.jwt-secret=${JWT_SECRET}
app.expiration-milliseconds=86400000
```

The token expiration is 86,400,000 milliseconds (24 hours).

**Security:** Generate a strong, randomly generated Base64-encoded secret key and keep it outside your source code.

## Eureka Client Configuration

The application registers itself with the Eureka Discovery Server.

```properties
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

eureka.instance.prefer-ip-address=true
```

Eureka Server must be running on port `8761`.

## Apache Kafka Configuration

The application uses Apache Kafka for asynchronous messaging.

Kafka bootstrap server:

```properties
spring.kafka.bootstrap-servers=localhost:9092
```

Producer configuration:

```properties
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer

spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer

spring.kafka.producer.properties.spring.json.add.type=true
```

The producer uses:

* String serialization for message keys.
* JSON serialization for message values.

## Actuator Configuration

Spring Boot Actuator provides application monitoring and management endpoints.

```properties
management.endpoints.web.exposure.include=*
```

For production deployments, expose only the necessary endpoints and secure sensitive management endpoints.

## Running the Application

**1. Clone the repository**

```bash
git clone https://github.com/Edibilo/novabank-user-account-service.git
```

**2. Navigate into the project directory**

```bash
cd novabank-user-account-service
```

**3. Configure application properties**

Set your PostgreSQL credentials, JWT secret, Kafka server and Eureka server configuration.

**4. Build the application**

```bash
mvn clean install
```

**5. Run the application**

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8081
```

## Monitoring

After starting the application, you can access the Actuator endpoints:

```text
http://localhost:8081/actuator
```

## Learning Objectives

This project demonstrates practical implementation of:

* Building RESTful microservices using Spring Boot.
* Implementing JWT-based authentication.
* Integrating Spring Security.
* Managing relational databases using Spring Data JPA.
* Registering microservices with Netflix Eureka.
* Implementing asynchronous messaging using Apache Kafka.
* Monitoring applications using Spring Boot Actuator.
* Managing application configuration.

## Author

**Mohamed**

Software Developer | Java | Spring Boot | Microservices | React

## License

This project is developed for educational and software engineering practice.
