package com.routeone.fault;

public class HmacSignatureGenerationException extends Exception {

    public HmacSignatureGenerationException (String message, Throwable t) {
        super(message, t);
    }
}
