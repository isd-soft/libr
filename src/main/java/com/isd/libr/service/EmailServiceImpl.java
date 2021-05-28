package com.isd.libr.service;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender emailSender;

    @Override
    public void sendWarningMessage(SimpleMailMessage message) {
        emailSender.send(message);
    }

    @Override
    public void sendEmailNotification(String text,
                                      String... toEmails) {
        SimpleMailMessage message = createMessage(text, toEmails);
        emailSender.send(message);
    }

    private SimpleMailMessage createMessage(String text, String... toEmails) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toEmails);
        message.setSubject("Notification");
        message.setText(text);
        return message;
    }

}





