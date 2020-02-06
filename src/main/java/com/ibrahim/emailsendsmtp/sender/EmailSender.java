package com.ibrahim.emailsendsmtp.sender;

import java.io.UnsupportedEncodingException;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Ibrahim Chowdhury
 */
public class EmailSender {

    public void sendEmail(Session session, String toEmail, String fromEmail, String senderName, String body, String subject, String messageId, String[] attachments) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);

        message.setHeader("Content-type", "text/html; charset=UTF-8");
        message.setHeader("format", "flowed");
        message.setHeader("Content-Transfer-Encoding", "8bit");
        message.setHeader("References", messageId);
        message.setHeader("In-Reply-To", messageId);

        message.setFrom(new InternetAddress(fromEmail, senderName));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

        message.setSubject(subject, "UTF-8");
//        message.setText(body, "UTF-8");

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        if (attachments != null) {
            for (String attachment : attachments) {
                
                String fileName;
                
                if(attachment.contains("\\")){
                    
                    String[] splittedFile = attachment.replace("\\", "\\\\").split("\\\\");
                    fileName = splittedFile[splittedFile.length-1];
                }else{
                    String[] splittedFile = attachment.split("/");
                    fileName = splittedFile[splittedFile.length-1];
                }
                
                messageBodyPart = new MimeBodyPart();
                DataSource dataSource = new FileDataSource(attachment);
                messageBodyPart.setDataHandler(new DataHandler(dataSource));
                messageBodyPart.setFileName(fileName);
                multipart.addBodyPart(messageBodyPart);
            }
        }

        message.setContent(multipart);
        System.out.println("Message is Ready.");

        Transport.send(message);

        System.out.println("Email Sent Successfully.");
    }

}
