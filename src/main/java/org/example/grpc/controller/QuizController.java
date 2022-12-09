package org.example.grpc.controller;

import org.springframework.http.ResponseEntity;

public interface QuizController {

    ResponseEntity<?> getRandomQuestion();

}
