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
//        Properties properties = System.getProperties();
        System.out.println("------------------"+env.getProperty("smtp.gmail.com"));
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
//        System.out.println("--------26-----------"+properties.getProperty("smtp.gmail.com"));
//        mailSender.setHost(properties.getProperty("smtp.gmail.com"));
        mailSender.setPort(587);
//        mailSender.setUsername(properties.getProperty("spring.mail.username"));
//        mailSender.setPassword(properties.getProperty("spring.mail.password"));
//        mailSender.setPort(587);
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






//        Email email = EmailBuilder.startingBlank()
//                .from("From", "hediye853@gmail.com")
//                .to("To", "sarait405@gmail.com")
////                .to("You too", "you@example.com")
//                .withSubject(subject)
//                .appendText(text)
//                .buildEmail();
//
//
//
//        MailerBuilder
////                .withSMTPServer("smtp.mailtrap.io", 2525, "1a2b3c4d5e6f7g", "1a2b3c4d5e6f7g")
////                .withSMTPServer("smtp.mailtrap.io", 25, "1a2b3c4d5e6f7g", "1a2b3c4d5e6f7g")
////                .withSMTPServer("smtp.mailtrap.io", 587, "1a2b3c4d5e6f7g", "1a2b3c4d5e6f7g")
//                .withSMTPServer("smtp.mailtrap.io", 2525, "hediye853", "hediye1371")
//                .withTransportStrategy(TransportStrategy.SMTP_TLS)
//                .buildMailer()
//                .sendMail(email);
//        Properties props = new Properties();
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
//                    }
//                });
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress("hediye853@gmail.com"));
//        message.setRecipients(Message.RecipientType.TO,
//                InternetAddress.parse(to));
//        message.setSubject(subject);
//        message.setText(text);

        // Put your HTML content using HTML markup

        // Send message
//        Transport.send(message);

//        Email email = new Email();
//
//        email.setFromAddress("Michel Baker", "myEMAIL@gmail.com");
//        email.addRecipient("mom", "otherEMAIL@gmail.com", RecipientType.TO);
//        email.setSubject("My Bakery is finally open!");
//        email.setText("Mom, Dad. We did the opening ceremony of our bakery!!!");
//
//        new Mailer("smtp.gmail.com", 587, "myEMAIL@gmail.com", "myPASSWORD",TransportStrategy.SMTP_TLS).sendMail(email);


//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("noreply@baeldung.com");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        javaMailSender.send(message);



    }
}
