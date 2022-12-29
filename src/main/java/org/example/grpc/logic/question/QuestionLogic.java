package org.example.grpc.logic.question;

import com.example.grpc.Question;
import com.example.grpc.QuestionId;
import com.example.grpc.QuestionList;

public interface QuestionLogic {
    Question getRandomQuestion();
    Question getQuestionById(String questionId);
    QuestionList getQuestionsByTheme(String theme);
    QuestionId createQuestion(Question question);
}
