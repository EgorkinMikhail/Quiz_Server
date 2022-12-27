package org.example.grpc.logic.question;

import com.example.grpc.Question;
import com.example.grpc.QuestionId;
import com.example.grpc.Theme;

public interface QuestionLogic {
    Question getRandomQuestion();
    Question getQuestionById(QuestionId questionId);
    Question getQuestionByTheme(Theme theme);
    QuestionId createQuestion(Question question);
}
