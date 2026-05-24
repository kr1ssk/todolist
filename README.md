# ToDo List API

Esta es una API REST desarrollada con Spring Boot 3 que diseñamos para resolver de manera eficiente la gestión diaria de tareas pendientes. La aplicación permite organizar actividades, realizar inserciones masivas de datos y filtrar listados según su estado actual (pendientes o completadas). Como característica clave, el sistema se integra mediante WebClient con la API externa oficial de Open-Meteo, permitiendo asociar la planificación de tareas con reportes climáticos en tiempo real y sin necesidad de claves de acceso.

## ¿Para qué se puede usar esta API?

Diseñamos este backend bajo una arquitectura estándar y limpia, lo que facilita que sea consumido de forma inmediata por distintas plataformas:
* **Herramientas de desarrollo (Postman / Insomnia):** Ideal para interactuar directamente con los endpoints, enviar objetos JSON y verificar de inmediato el comportamiento y las respuestas del servidor.
* **Aplicaciones Frontend (Web o Móviles):** Se puede conectar fácilmente con interfaces creadas en React, Angular, Vue o aplicaciones móviles nativas utilizando librerías de peticiones HTTP como Fetch o Axios.
* **Microservicios o sistemas de terceros:** Puede operar como un componente independiente dentro de un ecosistema de software que necesite centralizar la organización de tareas o consultar datos del clima de forma unificada.

---

## Guía de Endpoints (Documentación del Sistema)

Cuando la aplicación se está ejecutando localmente, la dirección base para interactuar con ella es `http://localhost:8080`.

### 1. Control y Gestión de Tareas

A través de estos endpoints se maneja toda la persistencia, consulta y modificaciones de las actividades dentro del sistema.

| Método | Enlace (Endpoint) | ¿Qué hace? |
| :--- | :--- | :--- |
| **GET** | `/api/v1/tareas` | Recupera el listado completo con todas las tareas almacenadas. |
| **GET** | `/api/v1/tareas/{id}` | Busca y muestra los detalles de una sola tarea usando su ID numérico. |
| **GET** | `/api/v1/tareas/completadas` | Filtra el sistema para mostrar únicamente las actividades ya resueltas. |
| **GET** | `/api/v1/tareas/pendientes` | Trae exclusivamente las tareas que aún quedan por hacer. |
| **POST** | `/api/v1/tareas` | Registra una nueva tarea (valida de forma automática que el título no venga en blanco). |
| **POST** | `/api/v1/tareas/muchas` | Permite

Cómo Clonar y Ejecutar el Proyecto
Sigue estos pasos para levantar la aplicación en tu entorno local:

1. Clonar el repositorio
Abre tu terminal de preferencia (como Git Bash) y ejecuta los siguientes comandos para descargar el código y entrar al directorio del proyecto:

Bash
git clone [https://github.com/kr1ssk/todolist.git](https://github.com/kr1ssk/todolist.git)
cd todolist

Equipo de Desarrolladores (Autores)
Este proyecto fue desarrollado de manera colaborativa por:

Cristian Rivera

Bruno Rivera

Fernando Valenzuela
