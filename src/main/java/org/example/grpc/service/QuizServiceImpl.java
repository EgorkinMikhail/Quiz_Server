package org.example.grpc.service;

import com.example.grpc.*;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.logic.question.QuestionLogic;
import org.example.grpc.logic.theme.ThemeLogic;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class QuizServiceImpl extends QuizServiceGrpc.QuizServiceImplBase {

    private final QuestionLogic questionLogic;
    private final ThemeLogic themeLogic;

    @Override
    public void getRandomQuestion(Empty request, StreamObserver<Question> responseObserver) {
        responseObserver.onNext(questionLogic.getRandomQuestion());
        responseObserver.onCompleted();
    }

    @Override
    public void getQuestionById(QuestionId request, StreamObserver<Question> responseObserver) {
        responseObserver.onNext(questionLogic.getQuestionById(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getQuestionByTheme(Theme request, StreamObserver<Question> responseObserver) {
        responseObserver.onNext(questionLogic.getQuestionByTheme(request));
        responseObserver.onCompleted();
    }

    @Override
    public void createQuestion(Question request, StreamObserver<QuestionId> responseObserver) {
        responseObserver.onNext(questionLogic.createQuestion(request));
        responseObserver.onCompleted();
    }

    @Override
    public void createTheme(Theme request, StreamObserver<ThemeId> responseObserver) {
        responseObserver.onNext(themeLogic.createTheme(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getTheme(ThemeId request, StreamObserver<Theme> responseObserver) {
        responseObserver.onNext(themeLogic.getTheme(request));
        responseObserver.onCompleted();
    }
}
