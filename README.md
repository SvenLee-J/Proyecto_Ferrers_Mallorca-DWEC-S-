# Proyecto_Ferrers_Mallorca-DWEC-S-

Proyecto Full Stack desarrollado con Spring Boot y Angular.
La aplicación permite a ferrers gestionar su perfil y productos,
y a clientes navegar por un catálogo público y realizar comandes.

## Fase 1. Setup i Autenticació JWT

Durante la primera semana se hará lo siguiente con el fin de tener hecho el `Setup y Autenticació JWT` de nuestra aplicación web:

### Setup del proyecto.

- **Backend** - Crear proyecto base Sprigng Boot.
- **Backend** - Configurar conexión a BD.
- **Backend** - Estructura básica de paquetes ( `controllers, services, repositories, entities`).
- **Backend** - Dependencias mínimas (`Web, Security, JWT, JPA, Driver BD`).

> Se busca que la API se levante sin errores y conecte con la BD.

- **Frontend** - Crear proyecto Angular.
- **Frontend** - Routing básico: (`/login, /register, /home`).
- **Frontend** - Servicio HTTP base.

> Se busca que la **app** arranque y funcione la navegación.

#### Commits para cada parte.

- `Commit: Fase 1 - SetUp Backend: Creacion proyecto SpringBoot.`  
- `Commit: Fase 1 - SetUp Backend: Configuracion conexiones a BD.` 
- `Commit: Fase 1 - SetUp Backend: Estructura Base del proyecto.`  
- `Commit: Fase 1 - SetUp Backend: Dependencias minimas(web,   security...).`
- `Commit: Fase 1 - SetUp Frontend: Routing básico: ( login, ....)`.
- `Commit: Fase 1 - SetUp Frontend: Servicio HTTP base.`

### Modelo de datos mínimo.

De momento solo tendremos el modelo `usuarios`. Ya que es el único necesario para esta primera fase.

```sql
usuaris (
  id,
  email,
  password,
  nom,
  rol
)
```

#### Commits.

`Commit: Fase 1 - Modelo de datos minimo v1.0`

### Autenticación JWT.

#### Backend.

##### Registro de usuario

- Encriptación password (BCrypt)
    
- Rol CLIENT / FERRER


##### Login

- Validación credenciales
    
- Generación JWT
    

##### Middleware / filtro JWT

- Proteger rutas privadas

#### Frontend

##### Formularios de login y registro

- Validación básica (required, email)

##### Guardar JWT

- LocalStorage / Service

##### Protección de rutas

- Guard / interceptor

#### Commits:

`Commit: Fase 1 - Autenticación JWT - Backend: Registro de usuario.`
`Commit: Fase 1 - Autenticación JWT - Backend: Login.`
`Commit: Fase 1 - Autenticación JWT - Backend: Middleware / filtro JWT.`

`Commit: Fase 1 - Autenticación JWT - Frontend: Formularios de login y registro.`
`Commit: Fase 1 - Autenticación JWT - Frontend: Guardar JWT.`
`Commit: Fase 1 - Autenticación JWT - Frontend: Protección de rutas.`

### Seguridad mínima.

- Roles en backend (`CLIENT / FERRER`)  
- Endpoint protegido de prueba

#### Commits:

`Commit: Fase 1 - Seguridad mínima, Rols en backend.`
`Commit: Fase 1 - Seguridad mínima, Endpoint protegido v1.0.`

---

## FASE 2. CRUD Perfils i Productes (Ferrers)

Durante la segunda semana se hara lo necesario para conseguir: `Perfils de Ferrer + CRUD Productes`.

### Ampliacion del modelo de datos minima.

```sql
usuaris (
  id,
  email,
  password,
  nom,
  rol
)

-- Apartir de aqui.

perfils_ferrer (
  id,
  usuari_id,
  nom_taller,
  localitat,
  descripcio
)

productes (
  id,
  ferrer_id,
  categoria_id,
  nom,
  preu,
  disponible
)

categories (
  id,
  nom
)
```

#### Commits:

`Commit: Fase 2 - Modelo de datos minimo v2.0`

### Desarrollo de Perfil de Ferrer.

#### Backend.

- Endpoint para crear perfil (solo rol FERRER)
- Endpoint para editar su propio perfil
- Endpoint para obtener perfil público

Seguiran las siguientes reglas: 
- Un usuario FERRER → un solo perfil.
- Un CLIENT → no puede crear perfil

#### Frontend.

- Vista “Mi perfil de ferrer”
- Formulario simple:
    - nombre del taller.
    - localitat.
    - descripcoó.

#### Commits:

`Commit: Fase 2 - Backend: Endpoints.`

`Commit: Fase 2 - Frontend: Vista de perfil personal ferrer.`
`Commit: Fase 2 - Frontend: Formularop simple taller.`


### CRUD de Productos.

#### Backend.
- Crear producto (solo FERRER)
- Listar productos del ferrer autenticado
- Editar producto propio
- Eliminar producto propio

Reglas mínimas:
- Un ferrer solo gestiona sus productos
- CLIENT no puede acceder

#### Frontend.

- Vista “Mis productos”
- Listado simple (tabla o cards)
- Formulario crear/editar:
    - nombre
    - precio
    - categoría
    - disponible

#### Commits:

`Commit: Fase 2 - CRUD Productos parte Backend.`

`Commit: Fase 2 - CRUD Productos parte Frontend.`

### Categorías (mínimo imprescindible)

#### Backend.

- Tabla categories
- Crud incompleto

> No necesitamos un CRUD completo para esta parte, solo un endpoint `GET /categorias`.

#### Frontend.

- Select de categorías en formulario producto

#### Commit:

`Commit: Fase 2 - Backend: Crud incompleto pero con lo suficiente.`

`Commit: Fase 2 - Frontend: Desarroyo de selector de categoria.`

### Seguridad y validaciones mínimas.

- Validar datos obligatorios
  - nombre producto
  - precio > 0
- Control de acceso por rol

#### Commit:

`Commit: Fase 2 - Valodaciones de datos obligatorias.`
`Commit: Fase 2 - Control de acceso por rol.`

---

## Fase 3. Catálogo público + Comandes

Durante esta fase se hará lo necesario para conseguir: `Catálogo público + Sistema básico de comandes`.

### Catálogo público.

#### Backend.

- Listar productos disponibles.
- Obtener detalle de un producto.
- Obtener perfil público del ferrer asociado al producto.

Endpoints mínimos:

- `GET /productes`
- `GET /productes/{id}`
- `GET /ferrers/{id}`

Reglas mínimas:

- Solo se muestran productos con `disponible = true`.
- No se implementa paginación ni ordenación avanzada.

> Se busca que cualquier usuario pueda navegar por el catálogo sin necesidad de autenticación.

#### Frontend.

- Vista de catálogo público (grid simple).
- Vista detalle de producto:

  - nombre
  - precio
  - información del ferrer
  - enlace a su perfil público

#### Commits:

`Commit: Fase 3 - Backend: Catálogo público de productos.`
`Commit: Fase 3 - Frontend: Vista de catálogo público.`
`Commit: Fase 3 - Frontend: Vista detalle de producto.`

---

### Navegación por categorías.

#### Backend.

- Filtro de productos por categoría mediante query params.

Ejemplo:

```
GET /productes?categoria_id=1
```

#### Frontend.

- Listado de categorías.
- Filtro simple del catálogo (select o botones).

#### Commits:

`Commit: Fase 3 - Backend: Filtro de productos por categoría.`
`Commit: Fase 3 - Frontend: Navegación por categorías.`

---

### Sistema de Comandes.

#### Ampliación del modelo de datos.

```sql
comandes (
  id,
  client_id,
  data,
  estat,
  total
)

linies_comanda (
  id,
  comanda_id,
  producte_id,
  quantitat,
  preu_unitari
)
```

#### Commits:

`Commit: Fase 3 - Modelo de datos de comandes y líneas.`

---

### Creación de comandes.

#### Backend.

- Crear comanda (solo CLIENT).
- Cálculo automático del total.
- Estado inicial: `PENDENT`.

Endpoint mínimo:

- `POST /comandes`

Reglas mínimas:

- No se implementa pago real.

#### Frontend.

- Carrito mínimo (estado en memoria).
- Botón “Realizar pedido”.

#### Commits:

`Commit: Fase 3 - Backend: Creación de comandes.`
`Commit: Fase 3 - Frontend: Carrito y creación de comandes.`

---

### Gestión de comandes por ferrer.

#### Backend.

- Listar comandes que incluyan productos del ferrer autenticado.
- Actualizar estado de la comanda.

Endpoints mínimos:

- `GET /comandes/meves`
- `PUT /comandes/{id}/estat`

Estados:

- PENDENT
- COMPLETADA

#### Frontend.

- Vista “Comandes recibidas”.
- Opción para cambiar el estado de la comanda.

#### Commits:

`Commit: Fase 3 - Backend: Gestión de comandes del ferrer.`
`Commit: Fase 3 - Frontend: Vista de comandes del ferrer.`

---

### Seguridad mínima.

- CLIENT solo puede crear comandes.
- FERRER solo puede gestionar comandes que incluyan sus productos.
- Control de acceso por rol.

#### Commits:

`Commit: Fase 3 - Seguridad y control de acceso en comandes.`

---

## Fase 4. Pulido final y documentación

Durante la última semana se realizará el `pulido final del proyecto`, la corrección de errores y la preparación de toda la documentación necesaria para la entrega y defensa.

### Testing y corrección de errores.

#### Backend.

- Revisión de endpoints.
- Comprobación de validaciones y respuestas HTTP.
- Control de errores básicos (400, 401, 403, 404).

#### Frontend.

- Revisión de flujos principales:

  - login
  - gestión de perfil
  - gestión de productos
  - catálogo público
  - comandes
- Corrección de errores de navegación.

> Se busca que el flujo completo de la aplicación funcione sin errores críticos.

#### Commits:

`Commit: Fase 4 - Testing y corrección de errores Backend.`
`Commit: Fase 4 - Testing y corrección de errores Frontend.`

---

### Limpieza y organización del código.

#### Backend.

- Eliminación de código no utilizado.
- Revisión de nombres de clases y métodos.
- Organización final de paquetes.

#### Frontend.

- Eliminación de componentes no usados.
- Organización de servicios y componentes.
- Revisión básica de estilos.

#### Commits:

`Commit: Fase 4 - Limpieza y refactorización Backend.`
`Commit: Fase 4 - Limpieza y refactorización Frontend.`

---

### Documentación del proyecto.

#### README.md.

- Descripción del proyecto.
- Instrucciones de instalación.
- Credenciales de prueba.
- Estructura del repositorio.

#### Documentación de la API.

- Listado de endpoints.
- Métodos HTTP.
- Breve descripción de cada endpoint.

> La documentación puede realizarse en Markdown, Postman o Swagger.

#### Commits:

`Commit: Fase 4 - Documentación README.`
`Commit: Fase 4 - Documentación de la API.`

---

### Preparación del vídeo de defensa.

- Introducción del alumno.
- Demo del MVP:
  - login
  - funcionalidad principal

- Explicación técnica:
  - una parte del backend
  - una parte del frontend

Duración:

- 3 – 5 minutos.

#### Commits:

`Commit: Fase 4 - Preparación vídeo de defensa.`

---

### Revisión final antes de la entrega.

- Comprobación de estructura del repositorio:
  - `/backend`
  - `/frontend`
  - `/database`

- Verificación de commits (>20).
- Última revisión general del proyecto.

#### Commit:

`Commit: Fase 4 - Revisión final y proyecto listo para entrega.`

> Nota: El proyecto implementa un MVP funcional.
Algunas funcionalidades se han simplificado para ajustarse
al alcance y tiempo disponible.