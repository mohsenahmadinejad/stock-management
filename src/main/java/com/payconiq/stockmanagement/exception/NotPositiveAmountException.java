package com.payconiq.stockmanagement.exception;

public class NotPositiveAmountException extends  RuntimeException{
    public NotPositiveAmountException() {
    }

    public NotPositiveAmountException(String message) {
        super(message);
    }

    public NotPositiveAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotPositiveAmountException(Throwable cause) {
        super(cause);
    }

    public NotPositiveAmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
