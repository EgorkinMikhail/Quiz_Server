package org.example.grpc.service.question;

import com.example.grpc.*;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.logic.question.QuestionLogic;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class QuestionServiceImpl extends QuestionServiceGrpc.QuestionServiceImplBase {

    private final QuestionLogic questionLogic;

    @Override
    public void getRandomQuestion(Empty request, StreamObserver<Question> responseObserver) {
        responseObserver.onNext(questionLogic.getRandomQuestion());
        responseObserver.onCompleted();
    }

    @Override
    public void getQuestionById(QuestionId request, StreamObserver<Question> responseObserver) {
        responseObserver.onNext(questionLogic.getQuestionById(request.getQuestionId()));
        responseObserver.onCompleted();
    }

    @Override
    public void getQuestionsByTheme(Theme request, StreamObserver<QuestionList> responseObserver) {
        responseObserver.onNext(questionLogic.getQuestionsByTheme(request.getThemeId()));
        responseObserver.onCompleted();
    }

    @Override
    public void createQuestion(Question request, StreamObserver<QuestionId> responseObserver) {
        responseObserver.onNext(questionLogic.createQuestion(request));
        responseObserver.onCompleted();
    }
}
