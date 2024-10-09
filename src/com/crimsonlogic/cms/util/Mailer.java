package com.crimsonlogic.cms.util;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Mailer {
    public static void send(String to, String subject, String msg) {
        final String user = ""; 
        final String pass = ""; 

        // 1st step) Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost"); // change accordingly
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "true");
              
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
//        props.put("mail.smtp.port", "465");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            });

        // 2nd step) Compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(msg);

            // 3rd step) Send message
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

