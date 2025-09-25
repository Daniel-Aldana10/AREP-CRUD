# Sistema de Gestión de Propiedades

## Resumen del Proyecto
Este sistema permite la gestión completa de propiedades, incluyendo la creación, lectura, actualización y eliminación (CRUD) de registros. Está diseñado para simplificar la administración de propiedades, proporcionando una interfaz intuitiva y un backend seguro que maneja las operaciones y persistencia de datos.

## Arquitectura del Sistema
El sistema se compone de tres capas principales:

1. **Frontend**
    - Desarrollado con HTML + JS.
    - Proporciona la interfaz de usuario para interactuar con las propiedades.
    - Se comunica con el backend mediante llamadas HTTP REST.

2. **Backend**
    - Desarrollado con Java y Spring Boot.
    - Expone APIs REST para gestionar propiedades y realizar operaciones CRUD.
    - Maneja la lógica de negocio, validaciones y seguridad.

3. **Base de Datos**
    - Utiliza MySQL para almacenar la información de las propiedades.
    - La estructura principal incluye tablas como `properties` para registrar cada propiedad.
    - El backend se conecta a la base de datos mediante JDBC/Hibernate para persistencia.

**Interacción entre componentes:**  
El frontend envía solicitudes HTTP al backend, que procesa la información y realiza las operaciones correspondientes sobre la base de datos. Los resultados se devuelven al frontend para su visualización.

## Diseño de Clases
Principales clases del sistema:

- **Property**: Representa una propiedad con atributos como `id`, `direccion`, `precio`, `tipo`, etc.
- **PropertyService**: Contiene la lógica de negocio para crear, actualizar, eliminar y consultar propiedades.
- **PropertyController**: Controlador que recibe las solicitudes HTTP del frontend y llama a los servicios correspondientes.

## Pruebas Unitarias (Tests)

El proyecto incluye pruebas unitarias usando JUnit 5 y Mockito para garantizar que la lógica del backend funcione correctamente. Se prueba la clase PropertyServiceimpl, que contiene la lógica de negocio para la gestión de propiedades.

**Cobertura de pruebas:**
**Crear propiedad (CREATE)**
  - Caso válido: Se verifica que una propiedad con datos correctos se guarde correctamente.
  - Casos inválidos:
    - Dirección vacía → lanza IllegalArgumentException.
    - Precio negativo o nulo → lanza IllegalArgumentException.
    - Descripción demasiado larga (>200 caracteres) → lanza IllegalArgumentException.

**Consultar todas las propiedades (FIND ALL)**
  - Se asegura que findAll() retorne la lista de propiedades correctamente.

**Consultar por ID (FIND BY ID)**
   - Encontrada: Devuelve la propiedad correspondiente.
   - No encontrada: Lanza PropertyNotFoundException.

**Actualizar propiedad (UPDATE)**
   - Encontrada y actualizada: Cambios se guardan correctamente.
   - No encontrada: Lanza PropertyNotFoundException.

**Eliminar propiedad (DELETE)**
   - Se verifica que se llame al método deleteById del repositorio.

**Herramientas utilizadas**
   - JUnit 5: Framework de pruebas unitarias en Java.
   - Mockito: Se utiliza para simular dependencias (PropertyRepository) y verificar interacciones.
### Cómo ejecutar el proyecto en local
# Clonar el repositorio
```
git clone https://github.com/Daniel-Aldana10/AREP-CRUD
cd AREP-CRUD
# Compilar el proyecto
mvn clean compile
# Archivo .env para conexión a MySQL en local, ponerlo en la raiz del proyecto
DB_URL=jdbc:mysql://localhost:3306/properties_db
DATABASE=properties_db
DB_USER=user
DB_USER=password
# Ejecutar el comando
docker run --env-file .env -p 3306:3306 --name mysql_local -d mysql:8
# Ejecutar la aplicación
java -jar target/crud-0.0.1-SNAPSHOT.jar
# Acceder a la aplicación
http://localhost:8080
```
## Link del video
[Enlace](https://pruebacorreoescuelaingeduco-my.sharepoint.com/:v:/g/personal/daniel_aldana-b_mail_escuelaing_edu_co/ERhjEB0fLBBMqJkAjaaIAoEBYMfq23YHXW3JhKoM8DXBvw?nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D&e=5Fvpfj)