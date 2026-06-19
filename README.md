# ToDoList API

Este proyecto corresponde a una API REST para la gestión de tareas pendientes (**ToDo List**), desarrollada bajo el framework **Spring Boot 3**. Cuenta con persistencia en base de datos relacional, un sistema de autenticación y autorización basado en tokens **JWT**, consumo de servicios climáticos externos y un set de pruebas unitarias automatizadas con **JUnit 5** y **Mockito**.

La arquitectura del sistema y su infraestructura están completamente contenerizadas mediante **Docker**, facilitando un despliegue limpio y consistente libre de configuraciones manuales locales.

---

## 🛠️ Tecnologías y Requisitos

* **Java**: Versión 17.
* **Spring Boot**: Versión 3.2.5.
* **Base de Datos**: MySQL 8.x (Instanciada automáticamente en un contenedor aislado).
* **Seguridad**: Spring Security + JSON Web Tokens (JWT via io.jsonwebtoken v0.12.5).
* **Documentación**: SpringDoc OpenAPI / Swagger UI v2.5.0.
* **Cliente Externo**: Spring WebFlux (WebClient para interactuar con la API Open-Meteo).
* **Pruebas**: JUnit 5 + Mockito para el testing de la capa lógica y de presentación.
* **Infraestructura**: Docker y Docker Compose.

---

## ⚙️ Configuración del Entorno (`.env`)

El proyecto utiliza un archivo de configuración `.env` en la raíz para orquestar de forma segura las credenciales esenciales utilizadas por Spring Boot y MySQL. Asegúrate de contar con las siguientes variables declaradas:

```env
# Configuración del Contenedor de la Base de Datos
MYSQL_ROOT_PASSWORD=miClaveSeguraMySQL2026
MYSQL_DATABASE=todolist_db
MYSQL_LOCAL_PORT=3307
MYSQL_DOCKER_PORT=3306

# Configuración de Spring Boot & Conectividad
SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/todolist_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=miClaveSeguraMySQL2026

# Token JWT Firma Secreta
JWT_SECRET=bibliotecaduoc-clave-secreta-jwt-2026-cambiar-en-produccion
🚀 Instrucciones de Ejecución y Despliegue Local
Sigue estos pasos en orden desde la terminal de tu entorno de desarrollo para compilar y levantar el ecosistema completo utilizando Docker:

Paso 1: Compilar y empaquetar la Aplicación
Genera el paquete ejecutable .jar de Spring Boot evadiendo la ejecución de los tests durante el empaquetado inicial:

Bash
./mvnw clean package -DskipTests
Paso 2: Construir y levantar los Contenedores
Inicia el proceso de orquestación de Docker Compose para descargar la imagen de MySQL, mapear los volúmenes, inyectar el entorno y levantar la API:

Bash
docker compose up --build
La aplicación estará lista una vez visualices en los logs el banner de Spring Boot y la confirmación: Started ToDoListApplication in ... seconds.

🔒 Control de Seguridad y Roles (JWT)
Todos los endpoints correspondientes al dominio del negocio se encuentran protegidos y requieren de un token de autenticación válido firmado por el servidor.

Reglas de Acceso Estructuradas por Spring Security:
Rutas Públicas (PermitAll):

/api/v1/auth/ (Registro e Inicio de sesión)

/swagger-ui/ y /v3/api-docs/ (Documentación del sistema)

Operaciones de Lectura (GET):

Requiere estar autenticado. Accesible para usuarios con rol ROLE_USER o ROLE_ADMIN.

Operaciones de Escritura, Edición y Borrado (POST, PUT, DELETE):

Rutas restringidas estrictamente. Solo los usuarios con rol ROLE_ADMIN están autorizados para mutar el estado de los recursos.

Flujo Operativo para Pruebas:
Registrar un Usuario: Envía un POST a /api/v1/auth/register con las credenciales elegidas. Por defecto recibirá el rol ROLE_USER.

Iniciar Sesión (Login): Envía un POST a /api/v1/auth/login con tus credenciales válidas. El servidor retornará una cadena cifrada que representa tu token JWT.

Adjuntar Token: En las subsecuentes llamadas a endpoints protegidos, debes incluir el siguiente Header HTTP:

Plaintext
Authorization: Bearer <tu_token_jwt>
📖 Documentación Interactiva (Swagger UI)
Una vez que el proyecto esté corriendo exitosamente mediante Docker Compose, puedes ingresar desde cualquier navegador para interactuar visualmente con los controladores expuestos y ejecutar pruebas en tiempo real:

👉 URL de Acceso: http://localhost:8080/swagger-ui/index.html

🧪 Pruebas Unitarias Automatizadas (JUnit 5 + Mockito)
El proyecto incluye un set robusto de pruebas automatizadas destinadas a asegurar de forma aislada e independiente el correcto comportamiento del controlador de tareas (TareaController) simulando las llamadas hacia la capa lógica mediante Mockito.

Se evalúan de forma estricta los siguientes 4 escenarios mínimos requeridos:

test1_CrearTareaExitosamente: Comprueba la creación de un ítem retornando un estado de respuesta HTTP 201 Created.

test2_ListarTodasLasTareas: Verifica la correcta recopilación masiva de tareas y el retorno de un estado de respuesta exitoso.

test3_ObtenerTareaPorIdExistente: Certifica la recuperación de datos puntuales bajo una ID válida.

test4_ObtenerTareaPorIdInexistente: Valida la gestión controlada de errores disparando un estado de respuesta HTTP 404 Not Found en caso de no localizar el registro.

Comando de Ejecución de Tests:
Para correr el set completo de pruebas automatizadas de manera aislada, ejecuta en tu terminal local:

Bash
./mvnw -Dtest=TareaControllerTest test
🛑 Detener la Aplicación de Forma Limpia
Para interrumpir la ejecución de los servicios, presiona las teclas Ctrl + C en la terminal activa. Posteriormente, ejecuta el siguiente comando para remover por completo los contenedores generados y liberar los puertos mapeados en tu máquina anfitriona:

Bash
docker compose down
