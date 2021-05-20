package com.isd.libr.service;

public class SamePasswordException extends Exception {
    public SamePasswordException(String message) {
        super(message);
    }
}
