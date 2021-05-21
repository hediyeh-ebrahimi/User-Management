package com.tutorial.userManagement.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender javaMailSender;
    private Environment env;

    @Autowired
    public EmailServiceImpl(Environment env) {
        this.env = env;
    }

    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("hediye853");
        mailSender.setPassword("hediye1371");
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        mailSender.setJavaMailProperties(properties);
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("hediye853@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);

    }
}
