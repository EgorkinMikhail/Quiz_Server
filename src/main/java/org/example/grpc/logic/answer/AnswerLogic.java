package org.example.grpc.logic.answer;

import com.example.grpc.Answer;
import com.example.grpc.AnswerId;

public interface AnswerLogic {
    AnswerId createAnswer(Answer answer);

    Answer getAnswer(AnswerId answerId);
}
