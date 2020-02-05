package com.ibrahim.emailsendsmtp.sender;

import java.io.UnsupportedEncodingException;
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

    public void sendEmail(Session session, String toEmail, String fromEmail, String senderName, String body, String subject, String messageId) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);

        message.setHeader("Content-type", "text/html; charset=UTF-8");
        message.setHeader("format", "flowed");
        message.setHeader("Content-Transfer-Encoding", "8bit");
        message.setHeader("References", messageId);
        message.setHeader("In-Reply-To", messageId);
        
        
        message.setFrom(new InternetAddress(fromEmail, senderName));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        
        message.setSubject(subject, "UTF-8");
        message.setText(body, "UTF-8");
//        long a = 1580729947;
//        message.setSentDate(new Date(1580729947));
        
        System.out.println("Message is Ready.");

        Transport.send(message);

        System.out.println("Email Sent Successfully.");
    }

}
