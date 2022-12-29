package org.example.grpc.controller.quiz;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface QuizController {

    ResponseEntity<?> getRandomQuestion() throws IOException;
    ResponseEntity<?> getQuestionById(String questionId) throws IOException;
    ResponseEntity<?> getQuestionsByTheme(String theme) throws IOException;


}
