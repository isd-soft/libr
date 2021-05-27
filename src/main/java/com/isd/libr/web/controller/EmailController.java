package com.isd.libr.web.controller;

import com.isd.libr.service.BookActionService;
import com.isd.libr.service.EmailService;
import com.isd.libr.web.dto.requests.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {
    private final EmailService emailService;


    @PostMapping()
    public ResponseEntity<?> sendNotification(@RequestBody EmailRequest request){
        emailService.sendEmailNotification(request);
        return ResponseEntity.ok().build();
    }











}
