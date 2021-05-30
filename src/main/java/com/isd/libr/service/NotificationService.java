package com.isd.libr.service;

import com.isd.libr.web.dto.requests.EmailRequest;

public interface NotificationService {
    void sendNotification(EmailRequest request);
}
