package org.example.exceptions;

import org.springframework.http.HttpStatus;

public class QuizExceptions extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;

    public QuizExceptions(String message, HttpStatus httpStatus) {
        super();
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public QuizExceptions(String message) {
        super();
        this.message = message;
        this.httpStatus = null;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
