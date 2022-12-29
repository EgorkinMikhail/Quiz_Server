package org.example.grpc.logic.question;

import com.example.grpc.Question;
import com.example.grpc.QuestionId;
import com.example.grpc.QuestionList;
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
import java.util.stream.Collectors;

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
            throw new QuizExceptions("Empty Question table in DB", HttpStatus.NOT_FOUND);
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
    public Question getQuestionById(String questionId) {
        Optional<QuestionEntity> questionEntityById = questionRepository.findById(questionId);
        return questionEntityById.map(questionEntity -> Question.newBuilder()
                .setQuestionId(questionEntity.getQuestionId())
                .setQuestion(questionEntity.getQuestion())
                .setAnswerId(questionEntity.getAnswerId())
                .setThemeId(questionEntity.getThemeId())
                .build()).orElseThrow(() -> new QuizExceptions("Question with Id "+questionId+" not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public QuestionList getQuestionsByTheme(String theme) {
        List<QuestionEntity> questionByName = questionRepository.getQuestionByTheme(theme);
        if (questionByName.isEmpty()) {
            throw new QuizExceptions("Questions with with theme "+theme+" not found", HttpStatus.NOT_FOUND);
        }
        return QuestionList.newBuilder()
                .addAllQuestionList(questionByName
                        .stream().map(questionEntity -> Question.newBuilder()
                                .setQuestionId(questionEntity.getQuestionId())
                                .setQuestion(questionEntity.getQuestion())
                                .setAnswerId(questionEntity.getAnswerId())
                                .setThemeId(questionEntity.getThemeId()).build()).collect(Collectors.toList())).build();
    }

    @Override
    public QuestionId createQuestion(Question question) {
        if (questionRepository.findById(question.getQuestionId()).isPresent()) {
            throw new QuizExceptions("Question ID is already in DB", HttpStatus.NOT_FOUND);
        }
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionId(question.getQuestionId());
        questionEntity.setQuestion(question.getQuestion());
        questionEntity.setThemeId(question.getThemeId());
        questionEntity.setAnswerId(question.getAnswerId());
        return QuestionId.newBuilder()
                .setQuestionId(questionRepository.save(questionEntity).getQuestionId())
                .build();
    }
}
