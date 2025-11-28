# Taller de Búsqueda: Arreglos y Listas Enlazadas Simples

Este proyecto implementa y compara diferentes algoritmos de búsqueda aplicados sobre dos estructuras de datos fundamentales: Arreglos (Arrays) y Listas Enlazadas Simples (SLL). El sistema carga datos reales desde archivos CSV utilizando la librería Apache Commons CSV y permite realizar búsquedas lineales, binarias y basadas en predicados.

## Integrantes
* Domenica Rojas
* Jaime Landazuri
* Yandri Piscocama
* Victor Roa

## Descripción del Proyecto

El objetivo principal es demostrar la implementación lógica de algoritmos de búsqueda sin depender de las colecciones nativas de alto nivel de Java para la lógica de búsqueda interna.

El proyecto incluye las siguientes implementaciones:

### 1. Estructuras de Datos
* **SimpleList:** Una implementación propia de una Lista Enlazada Simple genérica. Incluye métodos para insertar al inicio, al final, eliminar y buscar utilizando programación funcional (Predicates).

### 2. Algoritmos de Búsqueda en Arreglos (ArrayUtils)
* Búsqueda de la primera coincidencia.
* Búsqueda de la última coincidencia.
* Búsqueda de todas las coincidencias (findAll).
* Búsqueda Secuencial con Centinela.
* Búsqueda Binaria (requiere ordenamiento previo).

### 3. Algoritmos de Búsqueda en Listas (SimpleList)
* Búsqueda basada en condiciones (Predicates).
* findFirst (primera coincidencia).
* findLast (última coincidencia).
* findAll (retorna una lista de nodos que cumplen la condición).

### 4. Utilidades
* **CsvReader:** Utilidad para la lectura de columnas específicas de archivos CSV, convirtiendo los datos a tipos numéricos para su procesamiento.
* **SelectionSort:** Implementación del algoritmo de ordenamiento por selección, utilizado para preparar los datos antes de ejecutar una búsqueda binaria.

## Dependencias

Este proyecto utiliza la siguiente dependencia externa para el manejo de archivos de texto delimitados:

* **Apache Commons CSV** (Versión 1.14.1)
  * Se utiliza para el parseo robusto de los archivos de datos (`estudiantes_notas.csv`, `inventario_500_inverso.csv`, etc.).

## Requisitos Previos

* Java JDK 17 o superior.
* Apache Maven instalado y configurado en las variables de entorno.

## Instrucciones de Compilación

Para compilar el proyecto y descargar las dependencias necesarias (incluyendo Apache Commons CSV), abra una terminal en la raíz del proyecto (donde se encuentra el archivo `pom.xml`) y ejecute:

```bash
mvn clean install
````

Este comando limpiará compilaciones anteriores, descargará la librería commons-csv y compilará el código fuente generando los archivos `.class` en el directorio `target`.

## Instrucciones de Ejecución

Una vez compilado el proyecto, puede ejecutar la clase principal `SearchDemo` directamente desde Maven utilizando el siguiente comando en la terminal:

```bash
mvn exec:java -Dexec.mainClass="SearchDemo"
```

## Uso de la Aplicación

Al iniciar el programa, se desplegará un menú interactivo en la consola:

1.  **Selección de Archivo:** El usuario debe elegir uno de los 4 archivos CSV disponibles (Estudiantes, Inventario, Pacientes, Productos).
2.  **Carga de Datos:** El sistema leerá la columna numérica relevante del archivo seleccionado (por ejemplo, `edad` para estudiantes o `stock` para inventario).
3.  **Selección de Estructura:**
    * **Opción 1 (Array):** Ejecuta búsquedas secuenciales, con centinela y binaria sobre un arreglo estático.
    * **Opción 2 (SLL):** Convierte los datos a una Lista Enlazada Simple y ejecuta búsquedas utilizando predicados.
```
