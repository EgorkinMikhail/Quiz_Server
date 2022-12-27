package org.example.grpc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.converter.ProtoJsonConverter;
import org.example.exceptions.QuizExceptions;
import org.example.grpc.logic.question.QuestionLogic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(exposedHeaders = "errors, content-type")
public class QuizControllerImpl implements QuizController {

    private final QuestionLogic questionLogic;

    @Override
    @GetMapping("/question/getRandomQuestion")
    public ResponseEntity<?> getRandomQuestion() {

        try {
            return ResponseEntity.ok(ProtoJsonConverter.toJsonSimple(questionLogic.getRandomQuestion()));
        } catch (Exception e) {
            throw new QuizExceptions(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
