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