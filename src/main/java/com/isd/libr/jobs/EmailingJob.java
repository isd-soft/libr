package com.isd.libr.jobs;

import ch.qos.logback.classic.pattern.EnsureExceptionHandling;
import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.service.EmailService;
import com.isd.libr.service.UserService;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmailingJob {
    private final UserRepository userRepository;
    private final BookActionRepository bookActionRepository;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;

    @Value("${status.in.use.age}")
    private Integer inUseDaysAge;
    @Value("${spring.mail.username}")
    private String from;

    @Scheduled(cron = "${status.in.use.email.frequency}")
    public void checkIfStatusInUseIsMoreDays() throws MessagingException {
        String sign = "Libr Team";
        String[] usersEmails = bookActionRepository.findAllInUseOlderThen(inUseDaysAge)
                .stream()
                .map(ba -> ba.getUser().getEmail())
                .toArray(String[]::new);

        Context context = new Context();
        context.setVariable("sign",sign);
        context.setVariable("logo","images/logo.png");
        context.setVariable("userEmails",Arrays.asList(usersEmails));
        String templateName = templateEngine.process("emailUserTemplate.html", context);
        emailService.sendEmailNotification("Notification",templateName,usersEmails);

        String[] admins = userRepository.findByRole("ADMIN")
                .stream().map(User::getEmail)
                .toArray(String[]::new);

        String template = templateEngine.process("emailAdminTemplate.html",context);
        emailService.sendEmailNotification("Notification",template,admins);

    }

}
