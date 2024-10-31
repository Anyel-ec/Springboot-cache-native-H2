# Cache Native

Este proyecto es una API REST construida con Spring Boot que permite realizar operaciones CRUD en una entidad de `Producto`. Se utiliza una base de datos en memoria H2 para almacenar los datos y se ha implementado un sistema de caché para mejorar el rendimiento de las consultas.

## Tecnologías

- Java 17
- Spring Boot 3.3.5
- Spring Data JPA
- Spring Cache
- H2 Database (modo memoria)
- Lombok

## Estructura del Proyecto

El proyecto sigue una estructura organizada para separar las responsabilidades:

- **Controller**: `ProductoController` maneja las peticiones HTTP y define los endpoints de la API.
- **Service**: `ProductoService` gestiona la lógica de negocio y utiliza caché para optimizar el acceso a los datos.
- **Repository**: `ProductoRepository` es una interfaz que extiende `JpaRepository`, permitiendo la interacción con la base de datos.
- **Model**: `Producto` representa la entidad de negocio con sus atributos `id`, `nombre` y `precio`.

## Endpoints

La API ofrece los siguientes endpoints:

- `GET /api/productos`: Obtiene todos los productos (con caché).
- `GET /api/productos/{id}`: Obtiene un producto por su `id` (con caché).
- `POST /api/productos`: Crea un nuevo producto.
- `PUT /api/productos/{id}`: Actualiza un producto existente por su `id`.
- `DELETE /api/productos/{id}`: Elimina un producto por su `id`.

## Configuración

### Configuración de la base de datos

El proyecto usa una base de datos en memoria H2, configurada en el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```

### Configuración de caché

Se habilita el caché en la aplicación con la anotación `@EnableCaching` en la clase principal (`CacheNativeApplication`). Esto mejora el rendimiento al reducir las consultas a la base de datos en ciertas operaciones de lectura.

## Ejecución del Proyecto

Para ejecutar el proyecto:

1. Clona el repositorio y navega a la carpeta del proyecto.
2. Asegúrate de tener Maven instalado.
3. Ejecuta el siguiente comando:

   ```bash
   mvn spring-boot:run
   ```

La aplicación estará disponible en [http://localhost:8080](http://localhost:8080).

## Dependencias

Las dependencias se gestionan en el archivo `pom.xml`. Algunas de las principales incluyen:

- `spring-boot-starter-web`: Para crear servicios web RESTful.
- `spring-boot-starter-data-jpa`: Para la integración con JPA y persistencia de datos.
- `h2`: Base de datos en memoria.
- `lombok`: Para reducir el código boilerplate.
- `spring-boot-starter-test`: Para pruebas unitarias y de integración.

## Ejemplos de Uso

Puedes interactuar con la API utilizando herramientas como `curl` o Postman. Aquí hay algunos ejemplos:

### Crear un Producto

```bash
curl -X POST http://localhost:8080/api/productos -H "Content-Type: application/json" -d '{"nombre":"Producto 1", "precio":10.0}'
```

### Obtener todos los Productos

```bash
curl -X GET http://localhost:8080/api/productos
```

### Obtener un Producto por ID

```bash
curl -X GET http://localhost:8080/api/productos/1
```

### Actualizar un Producto

```bash
curl -X PUT http://localhost:8080/api/productos/1 -H "Content-Type: application/json" -d '{"nombre":"Producto Actualizado", "precio":12.0}'
```

### Eliminar un Producto

```bash
curl -X DELETE http://localhost:8080/api/productos/1
```

## Notas

- Al guardar, actualizar o eliminar un producto, el caché se invalida automáticamente para mantener la consistencia de datos.
- La API devuelve respuestas HTTP estándar para facilitar el manejo de errores en aplicaciones cliente.

## Autor

Proyecto creado por `Anyel EC`.
