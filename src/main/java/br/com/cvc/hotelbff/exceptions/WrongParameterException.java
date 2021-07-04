package br.com.cvc.hotelbff.exceptions;

public class WrongParameterException extends RuntimeException {
    public WrongParameterException(String msg) {
        super(msg);
    }
}
