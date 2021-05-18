package com.isd.libr.web.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateBooksStatusRequest {
    private Long bookId;
    private String newStatus;

    @JsonCreator

    public UpdateBooksStatusRequest(@JsonProperty Long bookId,
                                    @JsonProperty String newStatus) {
        this.bookId = bookId;
        this.newStatus = newStatus;
    }
}
