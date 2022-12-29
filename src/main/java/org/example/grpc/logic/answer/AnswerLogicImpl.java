package org.example.grpc.logic.answer;

import com.example.grpc.Answer;
import com.example.grpc.AnswerId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.db.AnswerRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerLogicImpl implements AnswerLogic {

    private final AnswerRepository answerRepository;
    @Override
    public AnswerId createAnswer(Answer answer) {
        return null;
    }

    @Override
    public Answer getAnswer(AnswerId answerId) {
        return null;
    }
}
