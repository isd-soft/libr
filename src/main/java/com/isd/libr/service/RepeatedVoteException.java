package com.isd.libr.service;

public class RepeatedVoteException extends RuntimeException {
    public RepeatedVoteException(String message) {
        super(message);
    }
}
