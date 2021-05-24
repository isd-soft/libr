package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddVoteRequest {
    private Long userId;
    private Long bookId;
    private Integer vote;

    @JsonCreator
    public AddVoteRequest(@JsonProperty Long userId,
                          @JsonProperty Long bookId,
                          @JsonProperty Integer vote) {
        this.userId = userId;
        this.bookId = bookId;
        this.vote = vote;
    }
}
