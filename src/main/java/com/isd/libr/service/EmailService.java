package com.isd.libr.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendSimpleMessage(SimpleMailMessage message);
}
