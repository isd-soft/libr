package com.isd.libr.web.controller;

import com.isd.libr.service.NotificationService;
import com.isd.libr.web.dto.requests.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<?> sendNotification(@RequestBody NotificationRequest request){
        notificationService.sendNotification(request);
        return ResponseEntity.ok().build();
    }











}
