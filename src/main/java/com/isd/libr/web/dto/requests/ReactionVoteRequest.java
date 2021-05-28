package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isd.libr.web.entity.ReactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReactionVoteRequest {
    private Long userId;
    private Long bookId;
    private ReactionType type;

    @JsonCreator
    public ReactionVoteRequest(@JsonProperty Long userId,
                               @JsonProperty Long bookId,
                               @JsonProperty ReactionType type) {
        this.userId = userId;
        this.bookId = bookId;
        this.type = type;
    }
}
