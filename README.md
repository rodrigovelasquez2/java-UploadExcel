# Excel-Java: Aplicación para Carga de Archivos Excel

## Descripción
Este proyecto es una aplicación Spring Boot que permite la carga y procesamiento de archivos Excel. La aplicación lee los datos de un archivo Excel y los almacena en una base de datos MySQL.

## Tecnologías utilizadas
- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- Apache POI (para manejo de archivos Excel)
- MySQL
- Maven

## Requisitos previos
- JDK 17 o superior
- Maven 3.x
- MySQL Server

## Configuración de la base de datos
Edita el archivo `src/main/resources/application.properties` para configurar tu conexión a MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_tu_bd
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

## Estructura del proyecto
- **controller**: Contiene los controladores REST
- **models**: Contiene DTOs y entidades
- **repository**: Interfaces para acceso a datos
- **services**: Servicios para la lógica de negocio
- **utils**: Clases utilitarias para el procesamiento de Excel

## Cómo ejecutar
1. Clona el repositorio:
   ```bash
   git clone https://github.com/rodrigovelasquez2/UploadExcel.git
   ```

2. Navega al directorio del proyecto:
   ```bash
   cd Excel-Java
   ```

3. Compila el proyecto:
   ```bash
   ./mvnw clean install
   ```

4. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

5. La aplicación estará disponible en `http://localhost:8080`

## API Endpoints
- **POST /api/excel/upload**: Permite cargar un archivo Excel
  - Parámetro: `file` (archivo Excel)
  - Respuesta: Mensaje de éxito o error

## Formato del archivo Excel
El archivo Excel debe contener las siguientes columnas para los productos:
- Nombre
- Descripción
- Precio
- (Otras columnas según la entidad Product)

## Licencia
Este proyecto está bajo la licencia [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

## Autor
Rodrigo Velasquez
