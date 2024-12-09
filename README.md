# PBHT

1.	Generar resumen ejecutivo que cuente con los siguientes elementos en el archivo README.md dentro del repositorio:
A.	Descripción, problema identificado, solución, arquitectura

Descripción
El sistema Gym Calimaya es una aplicación web diseñada para gestionar las asistencias y los usuarios de un gimnasio. Su propósito principal es facilitar el control de las entradas y salidas de los miembros, así como generar reportes detallados sobre su historial de asistencia, todo a través de una interfaz web fácil de usar.
Problema Identificado
Antes de este sistema, el gimnasio utilizaba métodos manuales para registrar las asistencias de los usuarios, lo que resultaba en inconsistencias, pérdida de datos y la falta de un sistema eficiente para generar reportes. El proceso era lento y propenso a errores humanos, lo que afectaba la calidad del servicio y la administración del gimnasio.
Solución
La solución es una plataforma web que permite a los administradores registrar y gestionar usuarios, tomar asistencia en tiempo real, filtrar asistencias por diferentes criterios (día, semana, mes, año) y descargar reportes en formatos como CSV. La aplicación ofrece una interfaz fácil de usar y un sistema robusto para mejorar la eficiencia administrativa y la precisión de los registros.
Arquitectura
La arquitectura del sistema está basada en el patrón Modelo-Vista-Controlador (MVC) y está desarrollada utilizando las siguientes tecnologías:
•	Frontend: HTML, CSS, Java (con un diseño responsive).
•	Backend: Servidor de aplicaciones Apache Tomcat utilizando Jakarta EE (Servlets y JSP).
•	Base de Datos: MySQL para almacenamiento de datos de usuarios y registros de asistencia.
•	Java: JDK 11 para la programación del servidor.
•	Herramientas adicionales: Maven para la gestión de dependencias y GitHub para el control de versiones.


B.	Tabla de contenidos (ToC) con enlaces o a la sección wiki dentro del repositorio o algún medio externo como ReadTheDocs.io

Tabla de Contenidos
1.	Descripción
2.	Requerimientos
  2.1	Servidores de aplicación, web, bases de datos, etc.
  2.2	Paquetes adicionales
  2.3	Versión de Java
3.	Instalación
  3.1	Cómo instalar el ambiente de desarrollo
  3.2	Cómo ejecutar pruebas manualmente
  3.3	Cómo implementar la solución en producción
4.	Configuración
  4.1	Configuración del producto
  4.2	Configuración de los requerimientos
5.	Uso
  5.1	Manual de usuario
  5.2	Manual de administrador
6.	Contribución
  6.1	Cómo contribuir
7.	Roadmap


Requerimientos:
A.	Servidores de aplicación, web, bases de datos, etc.
•	Servidor de aplicación: Apache Tomcat (versión 9 o superior).
•	Servidor web: No es necesario, ya que Apache Tomcat maneja tanto la parte de servidor web como de aplicaciones.
•	Base de datos: MySQL (versión 5.7 o superior).

B.	Paquetes adicionales.
•	JDBC para conexión con MySQL.
•	Maven para la gestión de dependencias.
•	JSP/Servlet API para el desarrollo de la lógica web.

C.	Versión de Java, etc.
•	Java: 11 o superior (especificado en pom.xml de Maven).
•	Maven: 3.6 o superior.


Instalación:
A.	¿Cómo instalar el ambiente de desarrollo?
1.	Clona el repositorio:
Ejecuta el siguiente comando para clonar el repositorio en tu máquina local:
git clone https://github.com/AL02970047/GymCalimaya.git
2.	Instala Maven: Si aún no tienes Maven, puedes instalarlo siguiendo las instrucciones aquí.
3.	Configura el entorno Java: Asegúrate de tener JDK 11 o superior instalado en tu máquina. Si no lo tienes, puedes descargarlo desde aquí.
4.	Configura la Base de Datos:
•	Crea una base de datos en MySQL llamada gym_calimaya.
•	Configura el archivo web.xml o el archivo de propiedades de conexión de la base de datos con las credenciales correctas.

B.	¿Cómo ejecutar pruebas manualmente?
•	Ejecuta el servidor Apache Tomcat en modo desarrollo.
•	Accede a la aplicación a través de http://localhost:8080/GymCalimaya.
•	Realiza pruebas funcionales accediendo a las funcionalidades principales: registrar usuarios, registrar asistencia, consultar reportes, etc.

C.	¿Cómo implementar la solución en producción en un ambiente local o en la nube como Heroku?
Ejecuta Apache Tomcat en tu máquina local.
Despliega el archivo .war generado por Maven en la carpeta webapps de Apache Tomcat.
El archivo .war generado se encontrará en el directorio target/. Colócalo en webapps/ y reinicia Tomcat.

Configuración:
A.	Configuración del producto (archivos de configuración).
El proyecto contiene archivos de configuración en el directorio src/main/resources/ que incluyen:
•	application.properties: Configuración de la base de datos y otros parámetros de la aplicación.
•	pom.xml: Configuración de Maven y las dependencias necesarias.

B.	Configuración de los requerimientos.
La configuración de los requerimientos depende del entorno en el que se desplegará la aplicación. Es importante asegurarse de que los valores de configuración como la URL de la base de datos, el puerto del servidor, etc., estén correctamente ajustados para el ambiente local o de producción.


Uso:
A.	Sección de referencia para usuario final. Manual que se hará referencia para usuarios finales.
B.	Sección de referencia para usuario administrador.


Contribución:
A.	Guía de contribución para usuarios.
B.	Debe contar con pasos específicos para clonar repositorio, crear un nuevo branch, enviar el pull request, esperar a hacer el merge

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


Roadmap:
A.	Requerimientos que se implementarán en un futuro.
Requerimientos que se implementarán en un futuro
•	Autenticación avanzada: Implementación de un sistema de autenticación con una cámara de reconocimiento facial o huella digital.
•	Mejoras en la interfaz de usuario: Rediseño de la interfaz para mejorar la experiencia del usuario.
•	Integración con servicios externos: Incorporación de APIs externas para enriquecer la funcionalidad de la aplicación.
•	Módulos de reportes.
