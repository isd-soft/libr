package com.isd.libr.web.controller;

import com.isd.libr.service.BookReactionService;
import com.isd.libr.web.dto.requests.BookReactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/book-reaction")

public class BookReactionController {
    private final BookReactionService bookReactionService;

    @PostMapping
    public ResponseEntity<?> addReaction(@RequestBody BookReactionRequest bookReactionRequest) {
        bookReactionService.react(bookReactionRequest);
        return ResponseEntity.ok().build();
    }
}
