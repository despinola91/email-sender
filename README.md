# Simple Mailer (Java + Maven)

Aplicación simple en Java para enviar correos electrónicos mediante SMTP (por ejemplo, Gmail).  
Permite ingresar los datos del servidor, autenticarse y enviar un mensaje directamente desde la consola.

---

## Requisitos

- **Java 17** o superior  
- **Maven 3.8+**  
- Una cuenta de correo con acceso SMTP habilitado  
  - Para Gmail, se requiere una **App Password** (ver: [Instrucciones de Google](https://support.google.com/accounts/answer/185833))

---

## Cómo usarlo

1. **Clonar el repositorio**

   ```bash
   git clone git@github.com:despinola91/email-sender.git
   cd email-sender
      
2. Compilar el proyecto

  ```bash
   mvn clean compile
````
3. Ejecutar la aplicación

  ```bash
  mvn exec:java
````
4. Completar los datos solicitados:
- SMTP host (por ejemplo, smtp.gmail.com)

- Puerto (587 para STARTTLS o 465 para SSL)

- Tipo de conexión (SSL o STARTTLS)

- Email y contraseña de la app

- Dirección de origen (From:)

- Dirección de destino (To:)

- Asunto y cuerpo del mensaje
El cuerpo se finaliza escribiendo un punto (.) en una línea nueva.
