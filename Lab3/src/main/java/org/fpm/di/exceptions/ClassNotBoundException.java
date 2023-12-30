package org.fpm.di.exceptions;

public class ClassNotBoundException extends RuntimeException {
    public ClassNotBoundException(String message) {
        super(message);
    }
}
