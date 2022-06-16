package com.project.shop.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String error) {
        super(error);
    }
}
