package com.ibrahim.emailsendsmtp.service;

import com.ibrahim.emailsendsmtp.configuration.EmailConfiguration;
import com.ibrahim.emailsendsmtp.sender.EmailSender;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.Session;

/**
 *
 * @author Ibrahim Chowdhury
 */
public class EmailSenderServiceImp implements IEmailSenderService{
    private EmailConfiguration configuration = new EmailConfiguration();
    private EmailSender emailSender = new EmailSender();

    @Override
    public Session configureEmailSMTP(String fromEmail, String password, 
            String host, boolean tlsEnable, boolean sslEnable) {
        return configureEmailSMTP(fromEmail, password, host, tlsEnable, sslEnable);
    }

    @Override
    public void sendEmail(Session session, String toEmail, String fromEmail, 
            String senderName, String body, String subject) throws MessagingException, UnsupportedEncodingException {
        emailSender.sendEmail(session, toEmail, fromEmail, senderName, body, subject);
    }
    
}
