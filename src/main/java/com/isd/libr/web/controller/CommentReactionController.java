package com.isd.libr.web.controller;

import com.isd.libr.service.CommentReactionService;
import com.isd.libr.web.dto.requests.CommentReactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/comment-reaction")

public class CommentReactionController {
    private final CommentReactionService commentReactionService;

    @PostMapping
    public ResponseEntity<?> addReaction(@RequestBody CommentReactionRequest commentReactionRequest) {
        commentReactionService.react(commentReactionRequest);
        return ResponseEntity.ok().build();
    }
}
