# Sistema de Gestión de Biblioteca Universitaria (API RESTful)

## 📋 Descripción del Proyecto
Backend robusto desarrollado con **Spring Boot 3** y **Java 21** para gestionar los procesos de una biblioteca universitaria. El sistema permite administrar libros, usuarios y el registro de préstamos, implementando seguridad avanzada y persistencia en base de datos relacional.

### 🚀 Tecnologías Principales
* **Framework:** Spring Boot 3 (Web, Data JPA, Security, Validation).
* **Base de Datos:** MySQL 8.
* **Seguridad:** JSON Web Tokens (JWT) con control de roles (ADMIN/USUARIO).
* **Documentación:** OpenAPI / Swagger UI.

---

## ⚙️ Arquitectura y Funcionalidades

### 1. Seguridad (JWT)
* **Autenticación sin estado (Stateless):** No se usan sesiones de servidor.
* **Protección de Rutas:**
    * `PUBLIC`: Login y Registro.
    * `ADMIN`: Crear libros, registrar préstamos.
    * `USUARIO`: Consultar catálogo y su propio historial.

### 2. Base de Datos (MySQL)
El sistema utiliza **Hibernate** para mapear automáticamente las entidades:
* `Usuario` <-> `Rol` (Many-to-Many).
* `Prestamo` -> `Usuario` (Many-to-One).
* `Prestamo` -> `Libro` (Many-to-One).

---

## 🛠️ Instrucciones de Instalación

1.  **Clonar el repositorio:**
    ```bash
    git clone [TU_LINK_DE_GITHUB_AQUI]
    ```

2.  **Configurar Base de Datos:**
    * Crear una base de datos vacía en MySQL llamada `biblioteca_db`.
    * Editar `src/main/resources/application.properties` con tus credenciales de MySQL.

3.  **Ejecutar la Aplicación:**
    ```bash
    mvn spring-boot:run
    ```

4.  **Configuración Inicial (Roles):**
    Una vez iniciada la app, ejecutar en MySQL:
    ```sql
    INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
    INSERT INTO roles (nombre) VALUES ('ROLE_USUARIO');
    ```

---

## 📌 Guía de Endpoints (Postman)

### 🔐 Autenticación
| Método | Ruta | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Registrar nuevo usuario. |
| `POST` | `/api/auth/login` | Iniciar sesión (Devuelve Token Bearer). |

### 📚 Libros (Requiere Token)
| Método | Ruta | Descripción | Rol Requerido |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/libros` | Listar catálogo. | USER / ADMIN |
| `POST` | `/api/libros` | Agregar nuevo libro. | **ADMIN** |

### 📖 Préstamos (Requiere Token)
| Método | Ruta | Descripción | Rol Requerido |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/prestamos` | Registrar préstamo (Libro + Usuario). | **ADMIN** |
| `GET` | `/api/prestamos/mis-prestamos` | Ver historial personal. | USER |

---

## 📄 Documentación API (Swagger)
Puede ver la documentación interactiva en:
`http://localhost:8080/swagger-ui/index.html`