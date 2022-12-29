package org.example.grpc.service.answer;

import com.example.grpc.Answer;
import com.example.grpc.AnswerId;
import com.example.grpc.AnswerServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.logic.answer.AnswerLogic;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class AnswerServiceImpl extends AnswerServiceGrpc.AnswerServiceImplBase {

    private final AnswerLogic answerLogic;

    @Override
    public void createAnswer(Answer request, StreamObserver<AnswerId> responseObserver) {
        responseObserver.onNext(answerLogic.createAnswer(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getAnswer(AnswerId request, StreamObserver<Answer> responseObserver) {
        responseObserver.onNext(answerLogic.getAnswer(request));
        responseObserver.onCompleted();
    }
}
