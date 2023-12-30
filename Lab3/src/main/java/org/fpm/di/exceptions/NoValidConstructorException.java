package org.fpm.di.exceptions;

public class NoValidConstructorException extends RuntimeException {
    public NoValidConstructorException(String message) {
        super(message);
    }
}
