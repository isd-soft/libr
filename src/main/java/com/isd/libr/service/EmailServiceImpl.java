package com.isd.libr.service;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.requests.EmailRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
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
    private final BookActionRepository bookActionRepository;
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
        Status status = request.getStatus();
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id %d doesn't exist ",request.getUserId()));
        }
        if(status.equals(Status.IN_USE) ||
                status.equals(Status.IN_LIBRARY) ||
                status.equals(Status.ORDERED)){
            Long bookId = request.getBookId();
            Optional<Book> book = bookRepository.findById(bookId);
            if(book.isPresent()){
                String title =  book.get().getTitle();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(user.get().getEmail());
        message.setSubject("Notification");
        message.setText("The book "+ title + " is " + status);
        emailSender.send(message);
    } }
        if(status.equals(Status.REQUESTED)){
            String[] admins = userRepository.findByRole("ADMIN")
                    .stream().map(User::getEmail)
                    .toArray(String[]::new);
            Long bookId = request.getBookId();
            Optional<Book> book = bookRepository.findById(bookId);
            String title;
            if(book.isPresent()){
              title =  book.get().getTitle();
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(from);
                message.setTo(admins);
                message.setSubject("Notification");
                message.setText(" The book  " + title  + " was requested" );
                emailSender.send(message);
            }
        }
    }

}





