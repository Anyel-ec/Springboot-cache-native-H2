# Cache Native

This project is a REST API built with Spring Boot that enables CRUD operations on a `Producto` entity. It uses an in-memory H2 database to store data and implements a caching system to improve query performance.

## Technologies

- Java 17
- Spring Boot 3.3.5
- Spring Data JPA
- Spring Cache
- H2 Database (in-memory mode)
- Lombok

## Project Structure

The project follows an organized structure to separate responsibilities:

- **Controller**: `ProductoController` handles HTTP requests and defines the API endpoints.
- **Service**: `ProductoService` manages business logic and uses caching to optimize data access.
- **Repository**: `ProductoRepository` is an interface that extends `JpaRepository`, enabling database interaction.
- **Model**: `Producto` represents the business entity with attributes `id`, `nombre`, and `precio`.

## Endpoints

The API provides the following endpoints:

- `GET /api/productos`: Retrieves all products (with caching).
- `GET /api/productos/{id}`: Retrieves a product by its `id` (with caching).
- `POST /api/productos`: Creates a new product.
- `PUT /api/productos/{id}`: Updates an existing product by its `id`.
- `DELETE /api/productos/{id}`: Deletes a product by its `id`.

## Configuration

### Database Configuration

The project uses an in-memory H2 database, configured in the `application.properties` file:

```properties
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```

### Cache Configuration

Caching is enabled in the application using the `@EnableCaching` annotation in the main class (`CacheNativeApplication`). This improves performance by reducing database queries for certain read operations.

## Running the Project

To run the project:

1. Clone the repository and navigate to the project folder.
2. Ensure Maven is installed.
3. Run the following command:

   ```bash
   mvn spring-boot:run
   ```

The application will be available at [http://localhost:8080](http://localhost:8080).

## Dependencies

Dependencies are managed in the `pom.xml` file. Key dependencies include:

- `spring-boot-starter-web`: To create RESTful web services.
- `spring-boot-starter-data-jpa`: For integration with JPA and data persistence.
- `h2`: In-memory database.
- `lombok`: To reduce boilerplate code.
- `spring-boot-starter-test`: For unit and integration testing.

## Usage Examples

You can interact with the API using tools like `curl` or Postman. Here are some examples:

### Create a Product

```bash
curl -X POST http://localhost:8080/api/productos -H "Content-Type: application/json" -d '{"nombre":"Producto 1", "precio":10.0}'
```

### Get All Products

```bash
curl -X GET http://localhost:8080/api/productos
```

### Get a Product by ID

```bash
curl -X GET http://localhost:8080/api/productos/1
```

### Update a Product

```bash
curl -X PUT http://localhost:8080/api/productos/1 -H "Content-Type: application/json" -d '{"nombre":"Updated Product", "precio":12.0}'
```

### Delete a Product

```bash
curl -X DELETE http://localhost:8080/api/productos/1
```

## Notes

- When saving, updating, or deleting a product, the cache is automatically invalidated to maintain data consistency.
- The API returns standard HTTP responses to facilitate error handling in client applications.

## Author

Project created by `Anyel EC`.