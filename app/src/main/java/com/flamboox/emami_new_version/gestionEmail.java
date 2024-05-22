package com.flamboox.emami_new_version;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class gestionEmail {
    private static String SMTP_HOST = "smtp.gmail.com";
    private static String SMTP_PORT = "587";//le port de gmail dans smtp
    private static final String EMAIL_ADDRESS = "emamis.nepasrepondre@gmail.com";
    private static final String PASSWORD = "CEPI05EMAmis";

    public static void sendPasswordResetEmail(String emailAddress, String newPassword) {
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EMAIL_ADDRESS, PASSWORD);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_ADDRESS));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
            message.setSubject("Réinitialisation du mot de passe emamis");
            message.setText("Votre nouveau mot de passe est : " + newPassword);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
}
}
