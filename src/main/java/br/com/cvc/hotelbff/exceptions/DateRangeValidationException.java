package br.com.cvc.hotelbff.exceptions;

public class DateRangeValidationException extends RuntimeException {
    public DateRangeValidationException(String msg) {
        super(msg);
    }

    public DateRangeValidationException(String msg, Throwable e) {
        super(msg, e);
    }
}
