package com.isd.libr.service;

public class BookDuplicateException extends RuntimeException {
    public BookDuplicateException(String message) {
        super(message);
    }
}
