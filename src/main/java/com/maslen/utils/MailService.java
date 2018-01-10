package com.maslen.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {

    static final String OUR_EMAIL = "MasLen";

    private static final String ENCRYPT_KEY = "Why_did_you_do_that?";

    private final MailSender mailSender;

    @Autowired
    MailService(@Qualifier(value = "mailSender") JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    private void sendMail(String from, String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public String decryptEmail(String email) {
        return Aes.decrypt(email, ENCRYPT_KEY);
    }

    public void sendRegistrationConfirmationEmail(String email) {
        String stringEmailAES = "";
        try {
            stringEmailAES = Aes.encrypt(email, ENCRYPT_KEY);
        } catch (Exception e) {
            //todo: надо нормально залогировать
            System.out.println("Шифронатор дал сбой");
        }
        //todo: реализовать на "токенах" хранящихся в БД с обределенным сроком

        String subject = "Confirm your registration on Maslen";
        String body = "Registration confirmation \n" +
                "In order to complete your registration on MessageApp, please, follow this link: " +
                "http://localhost:8080/confirmRegistration?p=" + stringEmailAES + "\n" +
                "If this email was sent to you by mistake, please, do not reply.";
        sendMail(OUR_EMAIL, email, subject, body);

    }
}
