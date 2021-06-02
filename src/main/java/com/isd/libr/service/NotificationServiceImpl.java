package com.isd.libr.service;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.exceptions.BookNotFoundException;
import com.isd.libr.service.EmailService;
import com.isd.libr.service.NotificationService;
import com.isd.libr.exceptions.UserNotFoundException;
import com.isd.libr.web.dto.requests.NotificationRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

/**
 * Implementation for {@link NotificationService}
 *
 * @author Maria Cojocari
 */
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final TemplateEngine templateEngine;

    @Override
    @Transactional
    public void sendNotification(NotificationRequest request) {
        String sign = "Libr Team";
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(String.format
                        ("User with id %d doesn't exist ", request.getUserId())));
        Book affectedBook = bookRepository.findById(request.getBookId())
                .orElseThrow(()-> new BookNotFoundException(String.format
                        ("Book with id %d doesn't exist ", request.getBookId())));
        Context context = new Context();
        context.setVariable("sign",sign);
        context.setVariable("logo","images/logo.png");
        context.setVariable("book",affectedBook.getTitle());
        context.setVariable("status",request.getStatus());
        String templateName = templateEngine.process("notificationForUser.html", context);
        try {
            emailService.sendEmailNotification("Notification",templateName,user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
