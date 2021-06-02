package com.isd.libr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;



@Service
@RequiredArgsConstructor
class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

//
//        @Override
//        public void sendWarningMessage (MimeMessage message){
//        emailSender.send(message);
//    }
//
//        @Override
//        public void sendEmailNotification (String text, String...toEmails) throws MessagingException {
//        MimeMessage message = crateHtmlMessage(text, toEmails);
//        emailSender.send(message);
//    }
//
//    private SimpleMailMessage createMessage(String text, String... toEmails) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(toEmails);
//        message.setSubject("Notification");
//        message.setText(text);
//        return message;
//    }


    @Override
    public void sendEmailNotification( String subject, String templateName, String... toEmails) throws MessagingException {
          MimeMessage message = crateHtmlMessage(templateName,toEmails);
          emailSender.send(message);
    }

    private MimeMessage crateHtmlMessage(String templateName, String... toEmails) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setFrom(from);
        messageHelper.setTo(toEmails);
        messageHelper.setSubject("Notification");
        messageHelper.setText(templateName,true);
        return message;

    }

}







