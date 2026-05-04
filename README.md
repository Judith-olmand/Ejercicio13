# 🗄️ Ejercicio13 [ConexionOracleMaven] — Integridad Referencial y DDL

Este proyecto Java gestionado con Maven automatiza la configuración de un esquema de base de datos relacional en Oracle. La aplicación se centra en la ejecución de sentencias **DDL** (Data Definition Language) para crear una estructura de tablas vinculadas mediante claves foráneas.

## 📋 Descripción del Proyecto
El propósito de este programa es inicializar un entorno de base de datos limpio y estructurado. El flujo de trabajo consiste en:
1.  **Limpieza Preventiva**: Intenta eliminar las tablas preexistentes (`departamento` y `empleado`) utilizando la cláusula `CASCADE CONSTRAINTS` para romper vínculos previos sin errores.
2.  **Definición de Esquema**: Crea la tabla maestra de departamentos y la tabla de empleados, estableciendo una relación de integridad referencial entre ambas.

## 🎯 Funcionalidades Principales
Al ejecutarse, el programa realiza las siguientes acciones de forma secuencial:
*   **Conexión JDBC**: Se conecta al servidor Oracle utilizando los parámetros de `db.properties`.
*   **Manejo de Errores de Borrado**: Captura excepciones si las tablas no existen para permitir que el script continúe sin detenerse.
*   **Creación de Tablas**:
    *   `departamento`: Con clave primaria `dep_id`.
    *   `empleado`: Con clave primaria `emp_id` y una **Clave Foránea** (`FK_departamento`) que referencia a la tabla de departamentos.
*   **Confirmación de Operaciones**: Notifica por consola la creación exitosa de cada objeto de la base de datos.

## 🏗️ Estructura del Proyecto
El proyecto mantiene la organización modular estándar de Maven para aplicaciones de consola:

```text
Ejercicio13 [ConexionOracleMaven]/
│
├── 📁 src/
│   └── 📁 main/
│       ├── 📁 java/
│       │   └── 📁 org/example/
│       │       ├── ☕ DBConfig.java        # Proveedor de configuración de conexión
│       │       └── ☕ Main.java            # Lógica de creación de esquema (DDL)
│       └── 📁 resources/
│           └── 📄 db.properties           # Credenciales de acceso externas
│
├── 📁 target/                             # Binarios generados por Maven
├── 📄 pom.xml                             # Gestión de dependencias (OJDBC11)
└── 📄 README.md                           # Documentación del proyecto
```

## 📄 Formato del Archivo de Entrada
El archivo `src/main/resources/db.properties` es esencial para la ejecución:
```properties
db.url=jdbc:oracle:thin:@localhost:1521:xe
db.user=tu_usuario
db.password=tu_contraseña
```

## 🚀 Compilación y Ejecución
### Requisitos
*   Java JDK 17 o superior.
*   Maven 3.8+.
*   Usuario con permisos de creación de tablas en Oracle Database.

### Comandos de Terminal
```bash
# Limpiar y compilar el proyecto
mvn clean compile

# Ejecutar el script de creación de tablas
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## 🔧 Características Técnicas Implementadas
*   **Cláusula CASCADE CONSTRAINTS**: Permite el borrado de tablas que son referenciadas por otros objetos.
*   **Integridad Referencial**: Definición de `CONSTRAINT FK_departamento` para asegurar la coherencia de los datos entre tablas.
*   **Bloques Try-Catch Anidados**: Gestión específica para procesos de limpieza donde el error es un escenario esperado si la tabla aún no ha sido creada.
*   **Tipos de Datos Oracle**: Uso de `NUMBER` y `VARCHAR2` para compatibilidad nativa.

## 🎮 Ejemplo de Uso Visual

**Salida en Consola (ejecución por primera vez):**
```bash
La tabla Departamento no existe
La tabla Empleado no existe
Tabla Departamento creada correctamente
Tabla empleado creada correctamente
```

---
**Autor:** Judith Olmedo Andrés  
*Ejercicio 13 - Definición de Esquemas Relacionales con JDBC y Oracle*