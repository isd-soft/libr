package com.isd.libr.service;

import com.isd.libr.web.dto.requests.EmailRequest;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendWarningMessage(SimpleMailMessage message);
    void sendEmailNotification(EmailRequest request);}
