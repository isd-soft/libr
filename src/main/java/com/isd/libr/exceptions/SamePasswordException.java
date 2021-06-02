package com.isd.libr.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class SamePasswordException extends Exception {
    public SamePasswordException(String message) {
        super(message);
    }
}
