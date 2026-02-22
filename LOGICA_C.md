# Logica de cada parte archivo.

# Backend

Estructura del backend:

```
Backend/
├─ src/
│  └─ main/
│    ├─ java/
│    │  └─ FerrersArtesans/
│    │     └─ com/
│    │        └─ Backend/
│    │           ├─ controller/
│    │           ├─ dto/
│    │           ├─ model/
│    │           ├─ repository/
│    │           ├─ security/
│    │           ├─ service/
│    │           └─ BackendApplication.java
│    └─ resources/
│       └─ application.properties
│
└─ pom.xml
```

## Controller.

Los **Controllers** se encargan de recibir peticiones **HTTP** y llamar al **servicio** correspondiente, su funcion es unicamente para la **entrada** y salida de **datos**.

> Un controler nunca tendra **logica** o acceso directo a la **BD**.

> El controller se crea al **final**, una vez creado **`service`**, **`repository`** y **`security`**.

```
Numeros = orden de cracion.

controller/
├─ AuthController.java
├─ CategoriaController.java
├─ FerrerController.java 
├─ ProductoController.java
├─ UserController.java 
└─ ValidationExceptionHandler.java 
```

## DTOs.

Los **DTOs** encargan de definir que datos **entran** y **salen** del backend, actuando como capa **intermedia** entre **entidad** y peticiones **HTTP**.

> Añade filtro de seguridad en los **tipos** de datos de **formularios**.

> Un **DTO** nunca accede a **BD** ni contiene **lógica** de **negocio**. Solo transporta datos y define su formato.

> Los **DTOs** se crean **después** de los **models**.

```
dto/
├─ JwtResponse.java
├─ LoginRequest.java
├─ MessageResponse.java
├─ RegisterRequest.java
├─ UpdatePerfilFerrerDTO.java
├─ UpdateUserRequest.java
└─ UserResponse.java
```

## Models.

Los **Modelos** reperenentan las entidades del proyecto, basicamente la estructura que se guarda en la base de datos.

> Cada classe model corresponde a una tabla de la base de datos.

> Los models se crean al principio porque el resto de componentes dependen de el.

> Los model solo definen la estructura de datos de la entidad.

```
model/
├─ Categoria.java
├─ PerfilFerrer.java
├─ Producto.java
└─ User.java
```

## Repository.

Los **Repositories** se encargan de gestionar el acceso a la base de datos mediante las interfaces de **JPA**

> Son la capa que conecta los modelos con el resto del backend

> Proporciona métodos **CRUD** listos para usar ***(findAll(), save(), deleteById(), etc.)*** y permite la cración de metodos personalizados.

> Los repositorios se crean después de los **modelos**, Sin esta capa, los servicios no tendrían forma de interactuar con los datos guardados.

```
repository/
├─ CategoriaRepository.java
├─ PerfilFerrerRepository.java
├─ ProductoRepository.java
└─ UserRepository.java
```

## Security.

El paquete **Security** se encarga de toda la configuración y control del sistema de autenticación y autorización del backend.

> Su función principal es asegurar las rutas de la API, validar los tokens JWT

> Esta capa se crea después de definir los modelos y repositorios de usuario.

Los componentes de security/ trabajan de forma conjunta: el JwtAuthenticationFilter intercepta las peticiones, el JwtTokenUtil gestiona los tokens, CustomUserDetailsService busca los usuarios en la base de datos, y SecurityConfig define las reglas globales.

```
security/
├─ CustomUserDetailsService.java
├─ JwtAuthenticationFilter.java
├─ JwtTokenUtil.java
└─ SecurityConfig.java
```

## Service.

Los **Services** encargan de manejar la logica de cada model del backend.

Son el punto medio entre los controladores y los repositorios: reciben las solicitudes del controlador, aplican las reglas o validaciones de negocio necesarias, y luego llaman al repositorio correspondiente para acceder a la base de datos.

> Esta capa debe ser completamente independiente del frontend y no tener ninguna lógica relacionada con la interfaz.

> Los servicios se crean después de los modelos y repositorios

```
service/
├─ AuthService.java
├─ CategoriaService.java
├─ PerfilFerrerService.java
├─ ProductoService.java
└─ UserService.java
```

# Frontend

Estructura de Frontend:

```
Frontend/
├─ src/
│  ├─ app/
│  │  ├─ components/
│  │  │  ├─ home/
│  │  │  ├─ login/
│  │  │  ├─ navbar/
│  │  │  ├─ register/
│  │  │  └─ users/
│  │  ├─ guards/
│  │  ├─ interceptors/
│  │  ├─ services/
│  │  ├─ app.html
│  │  ├─ app.routes.ts
│  │  └─ app.ts
```

## Componentes

### Home

Pagina principal de la web.

```
home/
├─ home.css
├─ home.html
├─ home.spec.ts
└─ home.ts
```

### Login

Pagina del login.

```
login/
├─ login.css
├─ login.html
├─ login.spec.ts
└─ login.ts
```

### Navbar

Navbar de la pagina.

```
navbar/
├─ navbar.css
├─ navbar.html
├─ navbar.spec.ts
└─ navbar.ts
```

### Register

Pagina del register.

```
register/
├─ register.css
├─ register.html
├─ register.spec.ts
└─ register.ts
```

### Users

Pagina de users. Solo muestra los usarios existentes, en caso de se admin.

```
users/
├─ users.css
├─ users.html
├─ users.spec.ts
└─ users.ts
```

## Guards

Los guards controlan el acceso a las rutas según el estado de autenticación del usuario.

```
guards/
├─ auth-guard.spec.ts
└─ auth-guard.ts
```

## Interceptors

Los Interceptors modifican las peticiones HTTP automáticamente antes de que salgan al servidor.

```
interceptors/
├─ jwt-interceptor.spec.ts
└─ jwt-interceptor.ts
```

## Services

Los Services se encargan de toda la comunicación HTTP con el backend y el manejo de datos.

```
services/
├─ auth.spec.ts
├─ auth.ts
├─ categorias.spec.ts
├─ categorias.ts
├─ ferrers.spec.ts
├─ ferrers.ts
├─ producto.spec.ts
└─ producto.ts
```