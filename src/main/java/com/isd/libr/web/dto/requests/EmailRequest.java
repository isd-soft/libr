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

    @JsonCreator
    public EmailRequest (@JsonProperty String emailType,
                         @JsonProperty Long userId) {

        this.emailType = EmailType.valueOf(emailType);
        this.userId = userId;

    }






}
