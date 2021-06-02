package com.isd.libr.service;

import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

public interface EmailService {

    void sendEmailNotification(String subject, String templateName, String... toEmails) throws MessagingException;


}
