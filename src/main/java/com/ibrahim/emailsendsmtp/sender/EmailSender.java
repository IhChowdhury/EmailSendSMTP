package com.ibrahim.emailsendsmtp.sender;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ibrahim Chowdhury
 */
public class EmailSender {
    
    public void sendEmail(Session session, String toEmail, String fromEmail, String senderName, String body, String subject) throws MessagingException, UnsupportedEncodingException{
        MimeMessage message = new MimeMessage(session);
        
        message.addHeader("Content-type", "text/html; charset=UTF-8");
        message.addHeader("format", "flowed");
        message.addHeader("Content-Transfer-Encoding", "8bit");
        
        message.setFrom(new InternetAddress(fromEmail, senderName));
//        message.setReplyTo(InternetAddress.parse(fromEmail, false));
        message.setSubject(subject,"UTF-8");
        message.setText(body,"UTF-8");
        message.setSentDate(new Date());
        
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail,false));
        
        System.out.println("Message is Ready.");
        
        Transport.send(message);
        
        System.out.println("Email Sent Successfully.");
        
        
    }
    
}
