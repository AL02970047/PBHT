# PBHT
# Gym Calimaya

## Tabla de Contenidos

1. [Descripción](#Descripción)
2. [Problema Identificado](#problema-identificado)
3. [Solución](#solución)
4. [Arquitectura](#arquitectura)
5. [Requerimientos](#requerimientos)
   - [Servidores y Bases de Datos](#servidores-y-bases-de-datos)
   - [Paquetes Adicionales](#paquetes-adicionales)
   - [Versión de Java](#versión-de-java)
6. [Instalación](#instalación)
   - [Ambiente de Desarrollo](#cómo-instalar-el-ambiente-de-desarrollo)
   - [Pruebas Manuales](#cómo-ejecutar-pruebas-manualmente)
   - [Implementación en Producción](#cómo-implementar-la-solución-en-producción)
7. [Configuración](#configuración)
   - [Configuración del Producto](#configuración-del-producto)
   - [Configuración de los Requerimientos](#configuración-de-los-requerimientos)
8. [Uso](#uso)
   - [Manual del Usuario Final](#manual-del-usuario-final)
   - [Manual del Administrador](#manual-del-administrador)
9. [Contribución](#contribución)
10. [Roadmap](#roadmap)




1.	Generar resumen ejecutivo que cuente con los siguientes elementos en el archivo README.md dentro del repositorio:
A.	Descripción, problema identificado, solución, arquitectura

## Descripción
El sistema Gym Calimaya es una aplicación web diseñada para gestionar las asistencias y los usuarios de un gimnasio. Su propósito principal es facilitar el control de las entradas y salidas de los miembros, así como generar reportes detallados sobre su historial de asistencia, todo a través de una interfaz web fácil de usar.
## Problema Identificado
Antes de este sistema, el gimnasio utilizaba métodos manuales para registrar las asistencias de los usuarios, lo que resultaba en inconsistencias, pérdida de datos y la falta de un sistema eficiente para generar reportes. El proceso era lento y propenso a errores humanos, lo que afectaba la calidad del servicio y la administración del gimnasio.
___
## Solución
La solución es una plataforma web que permite a los administradores registrar y gestionar usuarios, tomar asistencia en tiempo real, filtrar asistencias por diferentes criterios (día, semana, mes, año) y descargar reportes en formatos como CSV. La aplicación ofrece una interfaz fácil de usar y un sistema robusto para mejorar la eficiencia administrativa y la precisión de los registros.
___
## Arquitectura
La arquitectura del sistema está basada en el patrón Modelo-Vista-Controlador (MVC) y está desarrollada utilizando las siguientes tecnologías:
•	Frontend: HTML, CSS, Java (con un diseño responsive).
•	Backend: Servidor de aplicaciones Apache Tomcat utilizando Jakarta EE (Servlets y JSP).
•	Base de Datos: MySQL para almacenamiento de datos de usuarios y registros de asistencia.
•	Java: JDK 11 para la programación del servidor.
•	Herramientas adicionales: Maven para la gestión de dependencias y GitHub para el control de versiones.
___


## Requerimientos:
### Servidores de aplicación, web, bases de datos, etc.
•	Servidor de aplicación: Apache Tomcat (versión 9 o superior).
•	Servidor web: No es necesario, ya que Apache Tomcat maneja tanto la parte de servidor web como de aplicaciones.
•	Base de datos: MySQL (versión 5.7 o superior).
___
### Paquetes adicionales.
•	JDBC para conexión con MySQL.
•	Maven para la gestión de dependencias.
•	JSP/Servlet API para el desarrollo de la lógica web.
___
### Versión de Java, etc.
•	Java: 11 o superior (especificado en pom.xml de Maven).
•	Maven: 3.6 o superior.
___

## Instalación:
## ¿Cómo instalar el ambiente de desarrollo?
1.	Clona el repositorio:
Ejecuta el siguiente comando para clonar el repositorio en tu máquina local:
git clone https://github.com/AL02970047/GymCalimaya.git
2.	Instala Maven: Si aún no tienes Maven, puedes instalarlo siguiendo las instrucciones aquí.
3.	Configura el entorno Java: Asegúrate de tener JDK 11 o superior instalado en tu máquina. Si no lo tienes, puedes descargarlo desde aquí.
4.	Configura la Base de Datos:
•	Crea una base de datos en MySQL llamada gym_calimaya.
•	Configura el archivo web.xml o el archivo de propiedades de conexión de la base de datos con las credenciales correctas.

### ¿Cómo ejecutar pruebas manualmente?
•	Ejecuta el servidor Apache Tomcat en modo desarrollo.
•	Accede a la aplicación a través de http://localhost:8080/GymCalimaya.
•	Realiza pruebas funcionales accediendo a las funcionalidades principales: registrar usuarios, registrar asistencia, consultar reportes, etc.

### ¿Cómo implementar la solución en producción en un ambiente local o en la nube como Heroku?
Ejecuta Apache Tomcat en tu máquina local.
Despliega el archivo .war generado por Maven en la carpeta webapps de Apache Tomcat.
El archivo .war generado se encontrará en el directorio target/. Colócalo en webapps/ y reinicia Tomcat.

## Configuración:
### Configuración del producto (archivos de configuración).
El proyecto contiene archivos de configuración en el directorio src/main/resources/ que incluyen:
•	application.properties: Configuración de la base de datos y otros parámetros de la aplicación.
•	pom.xml: Configuración de Maven y las dependencias necesarias.

### Configuración de los requerimientos.
La configuración de los requerimientos depende del entorno en el que se desplegará la aplicación. Es importante asegurarse de que los valores de configuración como la URL de la base de datos, el puerto del servidor, etc., estén correctamente ajustados para el ambiente local o de producción.


## Uso:
A.	Sección de referencia para usuario final. Manual que se hará referencia para usuarios finales.
B.	Sección de referencia para usuario administrador.



# Manual de Uso
- ## Al ser un programa para la administración de un gym, solo se deja el manual de administrador. Solo es un software para administradores.

- ## Manual de Uso: `index.jsp` (Página Principal de Gym Calimaya)


### Descripción:
La página **index.jsp** sirve como la pantalla de bienvenida y acceso principal del sistema **Gym Calimaya**. Desde esta página, los administradores del gimnasio pueden navegar hacia diferentes secciones para gestionar usuarios, registrar asistencias y ver reportes.

### Funcionalidades:
1. **Mensajes Dinámicos:**
   - Esta página puede mostrar mensajes de éxito o error basados en los parámetros `message` o `error` pasados a través de la URL.
     - **Mensaje de Éxito:** Si se pasa un parámetro `message`, se mostrará un mensaje en un fondo verde con texto en blanco.
     - **Mensaje de Error:** Si se pasa un parámetro `error`, se mostrará un mensaje en un fondo rojo con texto en blanco.

2. **Botones de Navegación:** Los botones permiten acceder a diferentes funcionalidades del sistema:
   - **Registrar Usuario:**
     - **Función:** Redirige al formulario de registro de nuevos usuarios (`registrarUsuario.jsp`).
     - **Uso:** Permite a los administradores ingresar nuevos miembros al sistema de gestión del gimnasio.
   - **Tomar Asistencia:**
     - **Función:** Redirige a la página de toma de asistencia (`tomarAsistencia.jsp`).
     - **Uso:** Permite registrar la entrada o salida de los usuarios en el gimnasio.
   - **Listar Usuarios:**
     - **Función:** Redirige a la página donde se pueden listar todos los usuarios registrados en el gimnasio (`listarUsuarios.jsp`).
     - **Uso:** Muestra una lista de usuarios, permitiendo a los administradores ver detalles y realizar acciones adicionales, como filtros.
   - **Ver Lista de Asistencias:**
     - **Función:** Redirige a la página para ver el historial de asistencias de los usuarios (`filtrarAsistencia.jsp`).
     - **Uso:** Permite visualizar las asistencias filtradas por fechas (día, semana, mes, etc.) y generar reportes.

### Interacción:
- **Al hacer clic en un botón:** Cada botón está vinculado a una página específica del sistema. Al hacer clic en un botón, el administrador será redirigido a la página correspondiente.

---

## Manual de Uso: `registrarUsuario.jsp` (Formulario de Registro de Usuario)

### Descripción:
La página **registrarUsuario.jsp** permite a los administradores registrar nuevos usuarios en el sistema **Gym Calimaya**. Aquí, se ingresan los datos del usuario (nombre, correo, etc.) y se guardan en la base de datos.

### Funcionalidades:
1. **Formulario de Registro:**
   - Permite ingresar los detalles del usuario que será registrado en el sistema. Los campos incluyen:
     - **Nombre Completo**
     - **Correo Electrónico**
     - **Teléfono**
     - **Fecha de Inscripción**
     - Otros detalles relevantes según la configuración.
   - **Botón de Enviar:**
     - Al hacer clic en el botón de enviar, los datos se envían al servidor y se registran en la base de datos.

2. **Validación:**
   - El formulario realiza validaciones básicas para asegurarse de que los campos sean completados correctamente.

3. **Mensajes de Confirmación:**
   - Al registrar un usuario con éxito, se muestra un mensaje de confirmación en la misma página, indicando que el registro se ha completado con éxito.

### Interacción:
- **Ingreso de datos:** Completa los campos del formulario.
- **Envío del formulario:** Haz clic en el botón de enviar para registrar al usuario. Si el registro es exitoso, serás redirigido a la página principal con un mensaje de éxito.

---

## Manual de Uso: `tomarAsistencia.jsp` (Toma de Asistencia)

### Descripción:
La página **tomarAsistencia.jsp** permite registrar la entrada y salida de los usuarios en el gimnasio. El administrador puede marcar si un usuario está entrando o saliendo en el momento de su visita.

### Funcionalidades:
1. **Formulario de Asistencia:**
   - El administrador selecciona un usuario y registra su entrada o salida.
   - El formulario incluye un campo para seleccionar al usuario y un botón para marcar su asistencia.

2. **Validación:**
   - Se valida que el usuario esté registrado en la base de datos y que el tipo de asistencia (entrada o salida) esté correctamente seleccionado.

3. **Mensaje de Confirmación:**
   - Después de registrar la asistencia, se muestra un mensaje indicando si la acción fue exitosa.

### Interacción:
- **Selección del Usuario:** El administrador selecciona un usuario de una lista desplegable.
- **Registro de Asistencia:** Elige si el usuario está entrando o saliendo y haz clic en el botón de registrar.

---

## Manual de Uso: `listarUsuarios.jsp` (Listado de Usuarios)

### Descripción:
La página **listarUsuarios.jsp** permite visualizar todos los usuarios registrados en el gimnasio. Los administradores pueden ver detalles de cada usuario y filtrar los resultados según diferentes criterios.

### Funcionalidades:
1. **Lista de Usuarios:**
   - Muestra una lista de todos los usuarios con su nombre, correo electrónico, y detalles asociados.
   
2. **Filtros de Búsqueda:**
   - Permite a los administradores filtrar la lista de usuarios según ciertos criterios como nombre, fecha de inscripción, etc.
   
3. **Acciones por Usuario:**
   - Al lado de cada usuario, se incluyen botones para editar o eliminar registros.

### Interacción:
- **Filtrar Usuarios:** Ingresa criterios de búsqueda y presiona "Filtrar" para ajustar la lista.
- **Ver Detalles del Usuario:** Haz clic en el nombre del usuario para ver detalles más específicos o editar la información.

---

## Manual de Uso: `filtrarAsistencia.jsp` (Filtrado de Asistencias)

### Descripción:
La página **filtrarAsistencia.jsp** permite a los administradores visualizar las asistencias registradas de los usuarios, filtrándolas por diferentes períodos (día, semana, mes, año).

### Funcionalidades:
1. **Filtros de Asistencia:**
   - Los administradores pueden seleccionar un filtro de fechas (día, semana, mes, etc.) para ver las asistencias de los usuarios.
   
2. **Visualización de Asistencias:**
   - Muestra un reporte con todas las asistencias filtradas, incluyendo la entrada y salida de cada usuario.

3. **Generación de Reportes:**
   - La página permite descargar el reporte filtrado en formato CSV para su análisis posterior.

### Interacción:
- **Aplicar Filtros:** Selecciona el período de tiempo y haz clic en "Filtrar" para ver los resultados.
- **Descargar Reporte:** Haz clic en el botón de descarga para obtener el reporte en formato CSV.

---





## Contribución:
- A.	Guía de contribución para usuarios.
- B.	Debe contar con pasos específicos para clonar repositorio, crear un nuevo branch, enviar el pull request, esperar a hacer el merge

Si deseas contribuir al proyecto, sigue estos pasos:
1.	Clona el repositorio:
git clone https://github.com/AL02970047/PBHT
2.	Crea un nuevo branch para la tarea que vas a realizar:
git checkout -b nombre-de-tu-branch
3.	Realiza tus cambios y haz commit:
git add .
git commit -m "Descripción de los cambios"
4.	Envia tus cambios a GitHub:
git push origin nombre-de-tu-branch
5.	Abre un Pull Request en GitHub para que los cambios sean revisados.
6.	Espera la aprobación y el merge de tu pull request.
___

## Roadmap

### A. Requerimientos que se implementarán en un futuro.

- **Autenticación avanzada:** Implementación de un sistema de autenticación con una cámara de reconocimiento facial o huella digital.
- **Mejoras en la interfaz de usuario:** Rediseño de la interfaz para mejorar la experiencia del usuario.
- **Integración con servicios externos:** Incorporación de APIs externas para enriquecer la funcionalidad de la aplicación.
- **Módulos de reportes:** Mejoras en los módulos de reportes para generar análisis más detallados y personalizables.
___
