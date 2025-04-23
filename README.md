# weeklyvents: Cómo instalar y usar
## Requisitos de instalación
- Sistema operativo: Windows 10 o superior, o Ubuntu Desktop 22.04 LTS (Jammy Jellyfish) o cualquiera de sus derivados en su versión de escritorio
- Java SE 21

## Cómo instalar weeklyvents
### En Windows
Una vez descargado el JAR ejecutable, sólo basta con hacer doble clic sobre él para iniciar la aplicación.
### En Ubuntu
Abrir la terminal donde se encuentra el JAR ejecutable de weeklyvents: tiene el nombre de weeklyvents-0.1.0-SNAPSHOT-standalone.jar.

Ejecutar el siguiente comando para habilitar el permiso de ejecutar la aplicación libremente:

    chmod +x weeklyvents-0.1.0-SNAPSHOT-standalone.jar
    
Cerrar la terminal.

La aplicación ahora se puede iniciar al hacer doble clic sobre el archivo JAR.

## Cómo usar weeklyvents
### Registrar un evento
En el campo "Event name" introducir un nombre breve y significativo para identificar a un evento ocurrido en algún momento del día de la semana actual:

![Event name: \[Llegada de Pablo a la uni\]](https://github.com/AlexisC183/weeklyvents/blob/main/README%20resources/event-name.png)

En el campo "Elapsed time from beginning (mm:ss format)" introducir cuántos minutos y segundos han transcurrido desde que se produjo el evento, con dos dígitos para cada unidad temporal separada por dos puntos. Esto es necesario para dar tiempo al usuario a registrar dicho evento con precisión:

![Elapsed time from beginning (mm:ss format): \[01:20\]](https://github.com/AlexisC183/weeklyvents/blob/main/README%20resources/elapsed-time.png)

Hacer clic en el botón "Register event" para registrar el evento:

![\[Register event\]](https://github.com/AlexisC183/weeklyvents/blob/main/README%20resources/register-event.png)

### Visualizar eventos
Al iniciar la aplicación puede que se muestren o no algunos eventos previamente registrados en la zona derecha de la interfaz gráfica:

![\[====|12:25:31 Llegada de Pablo a la uni\]](https://github.com/AlexisC183/weeklyvents/blob/main/README%20resources/output.png)

weeklyvents solo mostrará aquellos eventos que han ocurrido entre una hora antes y una hora después de iniciada la aplicación, y que corresponden con el día de la semana actual.

![\[\[Refresh output\]|12:25:31 Llegada de Pablo a la uni\]](https://github.com/AlexisC183/weeklyvents/blob/main/README%20resources/output1.png)

Si la aplicación ha estado mucho tiempo abierta, se puede hacer clic en el botón "Refresh output" para recargar la interfaz de la zona derecha y mostrar los eventos cercanos al día-tiempo actual.

### El archivo events.txt
events.txt es un archivo legible por humanos que es creado al iniciar la aplicación o al hacer clic en el botón "Refresh output" cuando el archivo no existe. Es el archivo donde se registran los eventos y normalmente se encuentra junto al JAR ejecutable en Windows, o en el directorio `~` en Ubuntu.

weeklyvents incluye un editor del archivo events.txt para cuando se desea reparar este archivo o modificar o eliminar algunos eventos registrados.

Para abrir dicho editor, hay que hacer clic en el botón "Edit events file":

![Se muestra una captura de pantalla de la interfaz gráfica principal](https://github.com/AlexisC183/weeklyvents/blob/main/README%20resources/ui.png)