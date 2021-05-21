package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddCommentRequest {
    private long personId;
    private long bookId;
    private String comment;

    @JsonCreator
    public AddCommentRequest(@JsonProperty String comment,
                             @JsonProperty long personId) {
        this.comment = comment;
        this.personId = personId;
    }
}
