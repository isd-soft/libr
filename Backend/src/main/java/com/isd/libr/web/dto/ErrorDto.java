package com.isd.libr.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDto {
    String errorMessage;

    public static ErrorDto from(String errorMessage) {
        ErrorDto result = new ErrorDto();
        result.setErrorMessage(errorMessage);
        return result;
    }
}
