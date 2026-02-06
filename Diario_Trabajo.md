# Fase 1. Setup i Autenticació JWT.

## Fase 1 - SetUp Backend.

### Preparar el Proyecto Spring Boot.
Primero se ha Preparado un projecto de `Spring Boot` con: https://start.spring.io/

Con las siguientes dependencias.

- **Spring Boot DevTools**: Auto-reload (desarrollo) 
- **Spring Web**: REST APIs/controladores
- **Lombok**: Aligera la carga de Getter/Setters... (Autogenera).
- **Spring Data JPA**: ORM(Object-Relational Mapping) + Repositories.
- **H2 Database**: BD en memoria (desarrollo/testing)
- **Validation**: Validacion de datos entrantes en DTO.
- **Spring Security**: Autenticación/autorización base

Una vez colocado en el direcrtorio correcto se comprueba su funcionamiento ejuctando dentro de /Backend:

```bash
mvn spring-boot:run
```
Y accedemos a la URL: `http://localhost:8080`.

> Al introducir las credenciales que nos aporta el terminal nos llevara a una pagina con un error `404`, porque no esta mapeado. Eso es bueno quiere decir que todo funiona correctamente.

`Commit: Fase 1 - SetUP Backend/Preparacion del proyecto(Spring-Boot)`.

### Configurar conexión a BD.

> En esta fase inicial se utilizara H2, puesto que estaremos haciendo pruebas y es idieal para el desarroyo por su "memoria cache".

Accederemos a `/src/main/java/resorce/application.properties` y le añediremos lo necesario para el desarroyo del proyecto.

Crearemos `/src/main/java/FerrersArtesans/Backend/config/SecurityConfig.java`.  
> Esta archivo de momento tendra la configuracion de seguridad basica para H2.

Una vez echo comprovaremos si funciona:

```bash
mvn spring-boot:run
```
Y accedemos a la URL: `http://localhost:8080/h2-console`.

Comprovaciones:  
- Login:
    - User: as
    - Password: password


`Commit: Fase 1 - SetUP Backend/Configuracion Conexion BD`.
