package com.dario;

import com.dario.mailer.MailRequest;
import com.dario.mailer.MailService;
import com.dario.mailer.PasswordReader;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("SMTP host (p.ej., smtp.gmail.com): ");
            String host = sc.nextLine().trim();

            System.out.print("SMTP port (587=STARTTLS, 465=SSL): ");
            String port = sc.nextLine().trim();

            System.out.print("Usar SSL (y/N): ");
            boolean useSSL = sc.nextLine().trim().equalsIgnoreCase("y");

            System.out.print("Usar STARTTLS (y/N): ");
            boolean useStartTLS = sc.nextLine().trim().equalsIgnoreCase("y");

            System.out.print("Usuario SMTP (suele ser tu email): ");
            String username = sc.nextLine().trim();

            String password;
            try {
                password = PasswordReader.readPasswordWithAsterisk("Password SMTP (app password recomendado): ");
            } catch (IOException e) {
                throw new RuntimeException("Error leyendo la contraseña", e);
            }

            System.out.print("From: ");
            String from = sc.nextLine().trim();

            System.out.print("To: ");
            String to = sc.nextLine().trim();

            System.out.print("Asunto: ");
            String subject = sc.nextLine();

            System.out.println("Cuerpo (finalizá con una línea que contenga solo un punto .):");
            StringBuilder bodyBuilder = new StringBuilder();
            while (true) {
                String line = sc.nextLine();
                if (line.equals(".")) break;
                bodyBuilder.append(line).append("\n");
            }
            String body = bodyBuilder.toString();

            MailRequest req = new MailRequest(
                    host, port, username, password,
                    from, to, subject, body,
                    useSSL, useStartTLS
            );

            new MailService().send(req);
            System.out.println("¡Email enviado!");
        } catch (Exception ex) {
            System.err.println("Fallo al enviar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
