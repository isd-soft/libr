package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isd.libr.web.entity.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmailRequest {

    private Status status;
    private Long userId;
    private Long bookId;

    @JsonCreator
    public EmailRequest (@JsonProperty String status,
                         @JsonProperty Long userId,
                         @JsonProperty Long bookId)
    {

        this.status = Status.valueOf(status);
        this.userId = userId;
        this.bookId = bookId;

    }






}
