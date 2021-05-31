package com.isd.libr.service;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.requests.EmailRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void sendNotification(EmailRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(String.format
                        ("User with id %d doesn't exist ", request.getUserId())));
        Book affectedBook = bookRepository.findById(request.getBookId())
                .orElseThrow(()-> new BookNotFoundException(String.format
                        ("Book with id %d doesn't exist ", request.getBookId())));
        String text = String.format(" The book %s changed into %s",
                affectedBook.getTitle(),
                request.getStatus());
        emailService.sendEmailNotification(text, user.getEmail());
    }
}
