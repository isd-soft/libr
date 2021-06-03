package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isd.libr.web.entity.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NotificationRequest {

    private Status status;
    private Long userId;

    @JsonCreator
    public NotificationRequest(@JsonProperty("emailType") String status,
                               @JsonProperty Long userId)
    {

        this.status = Status.valueOf(status);
        this.userId = userId;
    }






}
