package com.example.demo.exceptions;

public class InsufficientAmountException extends RuntimeException{

    public InsufficientAmountException(String msg){
        super(msg);
    }
}
