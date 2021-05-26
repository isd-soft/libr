package com.isd.libr.web.controller;

import com.isd.libr.service.BookActionService;
import com.isd.libr.service.EmailService;
import com.isd.libr.web.dto.requests.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;


    @PostMapping()
    public ResponseEntity<?> sendNotification(@RequestBody EmailRequest request){
        emailService.sendEmailNotification(request);
        return ResponseEntity.ok().build();
    }











}
