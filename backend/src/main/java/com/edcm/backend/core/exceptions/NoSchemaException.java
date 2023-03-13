package com.edcm.backend.core.exceptions;

public class NoSchemaException extends RuntimeException {
    public NoSchemaException() {
    }

    public NoSchemaException(String s) {
        super(s);
    }

    public NoSchemaException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSchemaException(Throwable cause) {
        super(cause);
    }
}
