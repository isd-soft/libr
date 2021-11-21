package com.isd.libr.service;

import com.isd.libr.web.dto.requests.NotificationRequest;

/**
 * NotificationService is an interface for sending email notification purposes.
 *
 * <p>Preferred implementation {@code NotificationServiceImpl}</p>
 *
 * @author Maria Cojocari
 */

public interface NotificationService {


    /**
     * Creates an email notification from template and send it to User.
     *
     * @param request object of type {@link NotificationRequest} with fields: <ul>
     *                <li>Status</li>
     *                <li>User Id</li>
     *                <li>Book Id</li>
     *                </ul>
     * @since 1.0
     */

    void sendNotification(NotificationRequest request);
}
