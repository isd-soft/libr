package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddCommentRequest {
    private long userId;
    private long bookId;
    private String comment;

    @JsonCreator
    public AddCommentRequest(@JsonProperty String comment,
                             @JsonProperty long userId) {
        this.comment = comment;
        this.userId = userId;
    }
}
