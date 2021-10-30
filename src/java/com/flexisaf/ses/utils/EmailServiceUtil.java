/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flexisaf.ses.utils;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Email sender utility
 *
 * @author ushie
 */
public class EmailServiceUtil implements Serializable {

    public EmailServiceUtil() {

    }

    /**
     * Send email using JavaMail API
     *
     * @param emailGatewayConfig
     * @param receiverEmail
     * @param subject
     * @param messageText
     * @return
     */
    public boolean sendEmail(
            EmailGatewayConfig emailGatewayConfig,
            String receiverEmail,
            String subject, String messageText) {
        boolean sentSuccessfully = false;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", emailGatewayConfig.getEmailSMTPserver());
        props.put("mail.smtp.port", emailGatewayConfig.getSmtpServerPort());
        props.put("mail.smtp.socketFactory.port", emailGatewayConfig.getSmtpServerPort());
        props.put("mail.smtp.starttls.enable", "true");

        try {
            Authenticator auth = new SMTPAuthenticator(
                    emailGatewayConfig.getSenderEmailAddress(), emailGatewayConfig.getSenderPassword());

            Session session = Session.getInstance(props, auth);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailGatewayConfig.getSenderEmailAddress()));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiverEmail));

            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);
            System.out.println("Email send successfully.");

            sentSuccessfully = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in sending email.");
        }

        return sentSuccessfully;
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        String senderEmailId;
        String senderPassword;

        SMTPAuthenticator(String senderEmailId, String senderPassword) {
            this.senderEmailId = senderEmailId;
            this.senderPassword = senderPassword;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmailId,
                    senderPassword);
        }
    }

}
