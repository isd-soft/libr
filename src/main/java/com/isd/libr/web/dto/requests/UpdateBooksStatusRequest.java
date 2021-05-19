package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateBooksStatusRequest {
    private Long bookId;
    private Long personId;
    private String newStatus;

    @JsonCreator

    public UpdateBooksStatusRequest(@JsonProperty Long bookId,
                                    @JsonProperty Long personId,
                                    @JsonProperty String newStatus) {
        this.bookId = bookId;
        this.personId = personId;
        this.newStatus = newStatus;
    }
}
