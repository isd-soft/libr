package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isd.libr.web.entity.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RequestBookRequest {
    private Long bookId;
    private Long personId;
    private Status status;

    @JsonCreator
    public RequestBookRequest(@JsonProperty Long bookId,
                              @JsonProperty Long personId,
                              @JsonProperty Status status) {
        this.bookId = bookId;
        this.personId = personId;
        this.status = status;
    }
}
