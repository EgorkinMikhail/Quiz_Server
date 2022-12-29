package org.example.grpc.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.exceptions.QuizExceptions;
import org.example.grpc.model.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(QuizExceptions.class)
    public ResponseEntity<ErrorMessage> handleQuizException(QuizExceptions exception) {
        log.error(exception.getHttpStatus().toString());
        log.error(exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatus()).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorMessage> handleIOException(QuizExceptions exception) {
        log.error(exception.getHttpStatus().toString());
        log.error(exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatus()).body(new ErrorMessage(exception.getMessage()));
    }
}
