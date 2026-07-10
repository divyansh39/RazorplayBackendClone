package com.project.razorpay.common.exception;

public class BusinessRuleViolationException extends RuntimeException{

    private final String errorCode;

    public BusinessRuleViolationException(String errorCode, String errorDescription){
        super(errorDescription);
        this.errorCode=errorCode;
    }

}
