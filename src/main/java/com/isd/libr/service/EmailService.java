package com.isd.libr.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendWarningMessage(SimpleMailMessage message);

    void sendEmailNotification(String text, String... toEmails);
}
