package com.dario.mailer;

public class MailRequest {
    public final String host, port, username, password, from, to, subject, body;
    public final boolean useSSL, useStartTLS;

    public MailRequest(String host, String port, String username, String password,
                       String from, String to, String subject, String body,
                       boolean useSSL, boolean useStartTLS) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.useSSL = useSSL;
        this.useStartTLS = useStartTLS;
    }
}
