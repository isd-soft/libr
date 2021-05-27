package com.isd.libr.web.controller;

import com.isd.libr.service.CommentService;
import com.isd.libr.web.dto.requests.AddCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/")
    public ResponseEntity<?> addComment(@RequestBody AddCommentRequest request) {
        commentService.addComment(request);
        return ResponseEntity.ok().build();
    }
}
