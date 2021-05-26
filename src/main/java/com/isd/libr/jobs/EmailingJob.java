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
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmailingJob {
        private final UserRepository userRepository;
        private final BookActionRepository bookActionRepository;
        private final EmailService emailService;
        @Value("${status.in.use.age}")
        private Integer inUseDaysAge;
        @Value("${spring.mail.username}")
        private String from;

        @Scheduled(cron = "${status.in.use.email.frequency}")
        private void checkIfStatusInUseIsMoreDays() {
            String[] usersEmails = bookActionRepository.findAllInUseOlderThen(inUseDaysAge)
                    .stream()
                    .map(ba -> ba.getUser().getEmail())
                    .toArray(String[]::new);


            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(usersEmails);
            message.setFrom(from);
            message.setSubject("Warning");
            message.setText("Dear User, \n Give back the book,please");
            emailService.sendWarningMessage(message);

            String[] admins = userRepository.findByRole("ADMIN")
                    .stream().map(User::getEmail)
                    .toArray(String[]::new);


            SimpleMailMessage messageToAdmin = new SimpleMailMessage();
            messageToAdmin.setTo(admins);
            messageToAdmin.setFrom(from);
            messageToAdmin.setSubject("Warning");
            messageToAdmin.setText("Dear Admin,\n Users with emails: " + Arrays.toString(usersEmails) + " are keeping books more than 30 days");
            emailService.sendWarningMessage(messageToAdmin);

        }


    }
