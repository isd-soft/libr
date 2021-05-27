package com.isd.libr.service;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.requests.EmailRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.EmailType;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
class EmailServiceImpl implements EmailService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender emailSender;

    @Override
    public void sendWarningMessage(SimpleMailMessage message) {
        emailSender.send(message);
    }

    @Override
    public void sendEmailNotification(EmailRequest request) {
        Optional<User> user = userRepository.findById(request.getUserId());
        EmailType type = request.getEmailType();
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id %d doesn't exist ",request.getUserId()));
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(user.get().getEmail());
        message.setSubject("Notification");
        message.setText("" + type);
        emailSender.send(message);
    }

}





