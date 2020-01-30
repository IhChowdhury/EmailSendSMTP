package com.ibrahim.emailsendsmtp.test;

import com.ibrahim.emailsendsmtp.configuration.EmailConfiguration;
import com.ibrahim.emailsendsmtp.sender.EmailSender;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Session;

/**
 *
 * @author Ibrahim Chowdhury
 */
public class Tester {
    public static void main(String[] args){
        String fromEmail = "mahtab.uddinnn@gmail.com";
        String password = "qwerty142536";
        String host = "smtp.gmail.com";
        
        String toEmail = "test@linereflection.com";
        String body = "This is test";
        String subject = "Check Sender";
        EmailConfiguration configuration = new EmailConfiguration();
        EmailSender emailSender = new EmailSender();
        Session session = configuration.configureEmailSMTP(fromEmail, password, host, true, false);
        try {
            emailSender.sendEmail(session, toEmail, fromEmail, "test", body, subject);
        } catch (MessagingException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
