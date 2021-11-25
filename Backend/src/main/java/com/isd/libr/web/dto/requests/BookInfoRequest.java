package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isd.libr.web.entity.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

@Data
@NoArgsConstructor
public class BookInfoRequest {
    private Long id;
    private Status status;


    @JsonCreator
    public BookInfoRequest(@JsonProperty Long id,
                           @JsonProperty String status) {

        this.id = id;
        this.status = Status.valueOf(status);
    }

}
