package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isd.libr.web.entity.ReactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookReactionRequest {
    private Long userId;
    private Long bookId;
    private ReactionType type;

    @JsonCreator
    public BookReactionRequest(@JsonProperty Long userId,
                               @JsonProperty Long bookId,
                               @JsonProperty String type) {
        this.userId = userId;
        this.bookId = bookId;
        this.type = ReactionType.valueOf(type);
    }
}
