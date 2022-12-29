package org.example.grpc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.converter.ProtoJsonConverter;
import org.example.exceptions.QuizExceptions;
import org.example.grpc.logic.question.QuestionLogic;
import org.example.grpc.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
//@CrossOrigin(exposedHeaders = "errors, content-type")
public class QuizControllerImpl implements QuizController {

    private final QuestionLogic questionLogic;

    @Override
    @GetMapping("/question/getRandomQuestion")
    public ResponseEntity<?> getRandomQuestion() throws IOException {
        return ResponseEntity.ok(ProtoJsonConverter.toJsonSimple(questionLogic.getRandomQuestion()));
    }

    @Override
    @GetMapping("/question/getQuestionById")
    public ResponseEntity<?> getQuestionById(@RequestParam("id") String questionId) throws IOException {
        return ResponseEntity.ok(ProtoJsonConverter.toJsonSimple(questionLogic.getQuestionById(questionId)));
    }

    @Override
    @GetMapping("/question/{theme}")
    public ResponseEntity<?> getQuestionsByTheme(@PathVariable("theme") String theme) throws IOException {
        return ResponseEntity.ok(ProtoJsonConverter.toJsonSimple(questionLogic.getQuestionsByTheme(theme)));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(QuizExceptions.class)
    public ErrorMessage handleQuizException(QuizExceptions exception) {
        return new ErrorMessage(exception.getMessage());
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorMessage> handleIOException(QuizExceptions exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(new ErrorMessage(exception.getMessage()));
    }
}
