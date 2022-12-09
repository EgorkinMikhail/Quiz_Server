package org.example.grpc.service;

import com.example.grpc.*;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.logic.QuestionLogic;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class QuestionServiceImpl extends QuizServiceGrpc.QuizServiceImplBase {

    private final QuestionLogic questionLogic;
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
        super.getQuestionByTheme(request, responseObserver);
    }

    @Override
    public void createQuestion(Question request, StreamObserver<QuestionId> responseObserver) {
        super.createQuestion(request, responseObserver);
    }

    @Override
    public void createTheme(Theme request, StreamObserver<ThemeId> responseObserver) {
        super.createTheme(request, responseObserver);
    }

    @Override
    public void getTheme(ThemeId request, StreamObserver<Theme> responseObserver) {
        super.getTheme(request, responseObserver);
    }
}
