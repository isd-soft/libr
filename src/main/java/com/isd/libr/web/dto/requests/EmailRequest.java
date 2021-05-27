package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isd.libr.web.entity.EmailType;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmailRequest {

    private EmailType emailType;
    private Long userId;
    private String bookTitle;

    @JsonCreator
    public EmailRequest (@JsonProperty EmailType emailType,
                         @JsonProperty Long userId,
                         @JsonProperty String bookTitle) {

        this.bookTitle = bookTitle;
        this.emailType = emailType;
        this.userId = userId;

    }






}
