package com.hotel.auth.exception;

public class EmailAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = -7772319332970704548L;

    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
