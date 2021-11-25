package com.isd.libr.web.controller;

import com.isd.libr.service.VoteService;
import com.isd.libr.web.dto.requests.AddVoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {
    private final VoteService voteService;

    @PostMapping()
    public ResponseEntity<?> addVote(@RequestBody AddVoteRequest request) {
        return ResponseEntity.ok().body(voteService.vote(request));
    }

}
