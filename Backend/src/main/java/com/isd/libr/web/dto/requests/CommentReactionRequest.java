package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isd.libr.web.entity.ReactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommentReactionRequest {
    private Long userId;
    private Long commentId;
    private ReactionType type;

    @JsonCreator
    public CommentReactionRequest(@JsonProperty Long userId,
                                  @JsonProperty Long commentId,
                                  @JsonProperty String type) {
        this.userId = userId;
        this.commentId = commentId;
        this.type = ReactionType.valueOf(type);
    }
}
