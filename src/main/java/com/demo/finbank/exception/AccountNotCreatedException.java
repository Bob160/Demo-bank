package com.demo.finbank.exception;

public class AccountNotCreatedException extends RuntimeException{

    public AccountNotCreatedException(String message) {
        super(message);
    }
}
