package com.isd.libr.web.controller;

import com.isd.libr.service.RepeatedVoteException;
import com.isd.libr.service.VoteService;
import com.isd.libr.web.dto.requests.AddVoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {
    private final VoteService voteService;

    @PostMapping("/")
    public ResponseEntity<?> addVote(@RequestBody AddVoteRequest request) {
        voteService.vote(request);
        return ResponseEntity.ok().build();
    }

}
