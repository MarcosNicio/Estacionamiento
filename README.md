# Estacionamiento
Estacionamiento
# Documentación del Patrón MVC

El patrón MVC divide la aplicación en tres componentes principales:

- **Modelo (Model)**: Maneja la lógica de datos de la aplicación. En este proyecto, las clases del paquete `estacionamiento.modelo` representan el modelo.
- **Vista (View)**: Maneja la interfaz de usuario y presenta los datos. Las clases en el paquete `estacionamiento.vista` representan la vista.
- **Controlador (Controller)**: Actúa como intermediario entre el modelo y la vista, procesando la entrada del usuario y actualizando los modelos y las vistas. Las clases en el paquete `estacionamiento.controlador` representan el controlador.

## Descripción

Este proyecto es una aplicación para la gestión de un estacionamiento, implementada utilizando el patrón de diseño MVC (Modelo-Vista-Controlador). La aplicación permite registrar vehículos, generar tickets, y gestionar la entrada y salida de vehículos en el estacionamiento.

## Estructura del Proyecto

### Paquete `estacionamiento.modelo`

- **`ConexionBaseDeDatos.java`**: Clase que maneja la conexión a la base de datos.
- **`ProcesadorDeImagenes.java`**: Clase que procesa las imágenes capturadas.
- **`ReconocedorDeTexto.java`**: Clase que utiliza OCR para reconocer texto en las imágenes.
- **`TipoDeVehiculo.java`**: Clase que define los tipos de vehículos.
- **`Usuario.java`**: Clase que representa a los usuarios de la aplicación.
- **`VehiculoInfo.java`**: Clase que contiene la información de los vehículos.

### Paquete `estacionamiento.vista`

- **`DialogoRegistro.java`**: Diálogo para el registro de usuarios y vehículos.
- **`DialogoVehiculo.java`**: Diálogo para mostrar la información del vehículo.
- **`LoginMain.java`**: Pantalla de inicio de sesión.
- **`MapaEstacionamiento.java`**: Mapa interactivo del estacionamiento.
- **`PanelAdministracion.java`**: Panel de administración para gestionar usuarios y vehículos.
- **`PanelEstadisticasGrafico.java`**: Panel para mostrar estadísticas gráficas.
- **`Principal.java`**: Ventana principal de la aplicación.
- **`TemaUtil.java`**: Clase para manejar los temas de la interfaz.
- **`TicketsPanel.java`**: Panel para generar y mostrar tickets.
- **`VentanaCamara.java`**: Ventana para capturar imágenes desde la cámara.

### Paquete `estacionamiento.controlador`

- **`ControladorCamara.java`**: Controlador para manejar la lógica de la cámara.
- **`Principal.java`**: Punto de entrada principal de la aplicación.

## Instalación

### Requisitos Previos

- JDK 21
- Apache NetBeans (o cualquier IDE compatible con Java)

## Login

En este panel podemos observar dos botones, uno de empleado y otro de administrador, los cuales al ser pulsados abren su respectivo login.

### Login Usuario

En este apartado se ingresa como usuario, el cual es nuestro empleado dentro del estacionamiento. Al usuario se le solicita el correo y la contraseña, los cuales están registrados en una base de datos. Si no están en la base, no podrán acceder. Si se quiere registrar un nuevo usuario, esta acción la realiza el administrador; esto se explicará más adelante.

### Login Administrador

Este es el usuario con más poder dentro del sistema. Para ingresar como administrador, se le solicita un correo y una contraseña, que están registrados en una base de datos.

### Principal

Al ser logueado como Usuario o Administrador, aparecerá esta ventana, la cual incluye el mapa del estacionamiento, estadísticas y administración; en los últimos dos apartados sólo puede acceder el administrador.

En el mapa de estacionamiento se pueden observar los lugares del estacionamiento, ya sea que estén ocupados o no. Si un lugar está en verde, está libre; si es de color naranja, está en espera de ser ocupado; y si es de color rojo, significa que está siendo ocupado por un vehículo. Al seleccionar un lugar de color verde dirá que no está siendo ocupado, en cambio, si está de color rojo y lo seleccionamos, nos mostrará un panel del detalle del vehículo que está, el tiempo que lleva posicionado en ese lugar y si finalizamos el servicio. Cuando finalizamos un servicio, nos desplegará una ventana donde se muestran los datos del servicio y la opción de imprimir ticket, guardar y enviar a correo electrónico como PDF al cliente.

Hasta abajo del mapa se encuentra un botón llamado “Registrar Entrada de Vehículo”, el cual al ser seleccionado nos mostrará una ventana donde se mostrará la cámara y dos botones, siendo Reconocer y Captura.

**Reconocer**: Reconoce los caracteres de la placa y los escribe en el txt que se encuentra del lado derecho. En caso de que no sean los caracteres iguales a la placa, se vuelve a presionar hasta que sea igual al de la placa.

**Capturar**: Cuando damos clic se registra la placa dentro de la base de datos y a la vez se asigna ese vehículo en una posición dentro del estacionamiento. En caso de que ese vehículo ya esté dentro de la base de datos, dando a entender que ya está posicionado en el estacionamiento, aparece un mensaje que dice que la placa ya está registrada.

### Estadística

En este apartado se generan diferentes estadísticas como lo son: Ocupación, Historial de Ocupación, Uso de Tipo de Vehículo y Registro de Usuarios. Estas gráficas se generan a partir de las bases de datos. Si estas últimas se actualizan, también lo hacen las estadísticas.

### Panel de Administración

En este aspecto se muestran tanto los usuarios y administradores dentro del sistema en una tabla. Se muestra tanto el ID, Nombre, Correo, Contraseña, Rol y Fecha de Ingreso, estos datos vienen derivados de una base de datos. Los tres botones debajo manipulan a los usuarios y administradores dentro del sistema. Se explica a continuación cada uno:

- **Agregar Empleado**: Este botón solicita datos para agregar a un nuevo usuario.
- **Eliminar Empleado**: Al seleccionar este botón, podemos seleccionar una fila de los empleados y administradores, y se elimina.
- **Actualizar Datos**: Cuando lo presionamos, podremos seleccionar un empleado o administrador, y podremos editar los datos que contiene, al igual que cambiarle el rol ya sea para convertir un empleado a administrador.

## Extra

En la parte superior izquierda encontramos un ícono de un engranaje, al pulsar sobre él nos mostrará dos opciones: Cambiar tema y Cerrar sesión. Al seleccionar cambiar de tema, nos mostrará varias opciones predeterminadas de temas. Al dar clic en una, cambiará a ese estilo. La opción cerrar sesión nos cerrará la ventana independientemente del rol del usuario y nos regresará al login principal.

## Instrucciones de uso

NOTA: Despues de descargar el proyecto, en la carpeta lib, tendras que extraer la carpeta "opencv_java490" para que funcione el programa.

Descarga el siguiente documento para tener las instrucciones del programa:

[INSTRUCCIONES_ESTACIONAMIENTO.pdf](https://github.com/MarcosNicio/Estacionamiento/files/15493404/INSTRUCCIONES_ESTACIONAMIENTO.pdf)

## Script para base de datos

https://drive.google.com/drive/folders/1H1qlgQZz0q_WWdaL_hZFuBmTAF4YN4Z7?usp=drive_link


