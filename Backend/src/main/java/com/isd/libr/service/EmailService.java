package com.isd.libr.service;

import javax.mail.MessagingException;

/**
 * EmailService is an interface for sending email purposes.
 *
 * <p>Preferred implementation {@code EmailServiceImpl}</p>
 *
 * @author Maria Cojocari
 */


public interface EmailService {
    /**
     *
     * Creates an email from template and send it to User.
     * @param subject represents the subject of the email.
     * @param templateName html template,used to creating emails.
     * @param toEmails list of users email addresses
     * @throws MessagingException in case when an error occurs upon reception of a message.
     *
     * @since 1.0
     */

    void sendEmailNotification(String subject, String templateName, String... toEmails) throws MessagingException;


}
