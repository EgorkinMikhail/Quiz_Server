package org.example.grpc.logic;

import com.example.grpc.Question;
import com.example.grpc.QuestionId;
import com.example.grpc.Theme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.db.QuestionRepository;
import org.example.db.entities.QuestionEntity;
import org.example.exceptions.QuizExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionLogicImpl implements QuestionLogic {
    private final QuestionRepository questionRepository;

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        List<QuestionEntity> questionEntityList = questionRepository.findAll();
        int sizeOfQuestionList = questionEntityList.size();
        int randomIndex = 0;

        if (sizeOfQuestionList == 0) {
            throw new QuizExceptions("Empty Question DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (sizeOfQuestionList > 1) {
            randomIndex = random.nextInt(sizeOfQuestionList);
        }

        QuestionEntity questionEntity = questionEntityList.get(randomIndex);
        return Question.newBuilder()
                .setQuestionId(questionEntity.getQuestionId())
                .setQuestion(questionEntity.getQuestion())
                .setAnswerId(questionEntity.getAnswerId())
                .setThemeId(questionEntity.getThemeId())
                .build();
    }

    @Override
    public Question getQuestionById(QuestionId questionId) {
        Optional<QuestionEntity> questionEntityById = questionRepository.findById(questionId.getQuestionId());
        return questionEntityById.map(questionEntity -> Question.newBuilder()
                .setQuestionId(questionEntity.getQuestionId())
                .setQuestion(questionEntity.getQuestion())
                .setAnswerId(questionEntity.getAnswerId())
                .setThemeId(questionEntity.getThemeId())
                .build()).orElse(Question.newBuilder().build());
    }

    @Override
    public Question getQuestionByTheme(Theme theme) {
        return null;
    }

    @Override
    public QuestionId createQuestion(Question question) {
        return null;
    }
}
