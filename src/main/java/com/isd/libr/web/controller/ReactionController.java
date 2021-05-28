package com.isd.libr.web.controller;

import com.isd.libr.service.ReactionService;
import com.isd.libr.web.dto.requests.ReactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/reaction")

public class ReactionController {
    private final ReactionService reactionService;

    @PostMapping
    public ResponseEntity<?> addReaction(@RequestBody ReactionRequest reactionRequest) {
        reactionService.react(reactionRequest);
        return ResponseEntity.ok().build();
    }
}
