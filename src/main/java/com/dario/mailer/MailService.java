package com.dario.mailer;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailService {

    public void send(MailRequest req) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", req.host);
        props.put("mail.smtp.port", req.port);

        // Protocolos modernos y confianza en el host para el cert
        props.put("mail.smtp.ssl.protocols", "TLSv1.2 TLSv1.3");
        props.put("mail.smtp.ssl.trust", req.host);

        // Normalización: no mezclar SSL y STARTTLS
        boolean ssl = req.useSSL;
        boolean starttls = req.useStartTLS;

        if ("465".equals(req.port)) {
            ssl = true;
            starttls = false;
        } else if ("587".equals(req.port)) {
            ssl = false;
            starttls = true;
        } else if (ssl && starttls) {
            System.out.println("Aviso: No se puede usar SSL y STARTTLS a la vez. Se prioriza STARTTLS.");
            ssl = false;
            starttls = true;
        }

        props.put("mail.smtp.ssl.enable", String.valueOf(ssl));
        props.put("mail.smtp.starttls.enable", String.valueOf(starttls));
        if (starttls) {
            props.put("mail.smtp.starttls.required", "true");
        }

        // props.put("mail.debug", "true"); // activar si querés ver el diálogo SMTP

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(req.username, req.password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(req.from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(req.to));
        message.setSubject(req.subject);

        // Texto plano. Para HTML: message.setContent(req.body, "text/html; charset=utf-8");
        message.setText(req.body);

        Transport.send(message);
    }
}
