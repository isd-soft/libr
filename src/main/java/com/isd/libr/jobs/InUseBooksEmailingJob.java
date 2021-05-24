
package com.isd.libr.jobs;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.service.EmailService;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class InUseBooksEmailingJob {
    private final UserRepository userRepository;
    private final BookActionRepository bookActionRepository;
    private final EmailService emailService;
    @Value("${status.in.use.age}")
    private Integer inUseDaysAge;
    @Value("${spring.mail.username}")
    private String from;

    @Scheduled(cron = "${status.in.use.email.frequency}")
    private void checkIfStatusInUseIsMoreDays() {
        String[] allEmails = bookActionRepository.findAllInUseOlderThen(inUseDaysAge)
                .stream().map(ba -> ba.getUser().getEmail())
                .toArray(String[]::new);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(allEmails);
        message.setFrom(from);
        message.setSubject("Notification");
        message.setText("Dear User, \n Give back the book,please");
        emailService.sendSimpleMessage(message);

        SimpleMailMessage messageToAdmin = new SimpleMailMessage();
        messageToAdmin.setTo(userRepository.findEmailByRole());
        messageToAdmin.setFrom(from);
        messageToAdmin.setSubject("Notification");
        messageToAdmin.setText("Dear Admin,\n Users with emails: " + Arrays.toString(allEmails) + " are keeping books more than 30 days");
   emailService.sendSimpleMessage(messageToAdmin);
        }
}

