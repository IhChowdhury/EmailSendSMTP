package com.ibrahim.emailsendsmtp.service;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.Session;

/**
 *
 * @author Ibrahim Chowdhury
 */
public interface IEmailSenderService {
    
    public Session configureEmailSMTP(String fromEmail, String password, String host, int sslOrTlsEnable);
    
    public void sendEmail(Session session, String toEmail, String fromEmail, String senderName, String body, String subject, String messageId) throws MessagingException, UnsupportedEncodingException;
    
}
