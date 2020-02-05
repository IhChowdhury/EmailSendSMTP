package com.ibrahim.emailsendsmtp.configuration;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 *
 * @author Ibrahim Chowdhury
 */
public class EmailConfiguration {

    public Session configureEmailSMTP(String fromEmail, String password, String host, int sslOrTlsEnable) {

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }

        };
        Properties props = null;

        if (sslOrTlsEnable == 1) {
            props = getPropertiesForSSL(host);
        } else if (sslOrTlsEnable == 2) {
            props = getPropertiesForTLS(host);
        } else {
            System.out.println("Please define TLS or SSL.");
        }

        Session session = Session.getInstance(props, authenticator);

        return session;

    }

    private Properties getPropertiesForTLS(String host) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true");

        return props;
    }

    private Properties getPropertiesForSSL(String host) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        return props;
    }

}
