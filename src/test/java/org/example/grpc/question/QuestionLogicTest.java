package org.example.grpc.question;

import com.example.grpc.Question;
import com.example.grpc.QuestionId;
import org.apache.commons.lang3.StringUtils;
import org.example.db.QuestionRepository;
import org.example.grpc.logic.QuestionLogic;
import org.example.grpc.logic.QuestionLogicImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class QuestionLogicTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    QuestionRepository repository;

    @Test
    public void getRandomQuestionTest() {
        QuestionLogic questionLogic = new QuestionLogicImpl(repository);
        Question question = questionLogic.getRandomQuestion();
        System.out.println(question);
        assert StringUtils.isNotEmpty(question.getQuestionId());
    }

    @Test
    public void getQuestionByIdTest() {
        QuestionLogic questionLogic = new QuestionLogicImpl(repository);
        Question question = questionLogic.getQuestionById(QuestionId.newBuilder().setQuestionId("1").build());
        System.out.println(question.getQuestion());
        assert StringUtils.isNotEmpty(question.getQuestionId());
        question = questionLogic.getQuestionById(QuestionId.newBuilder().setQuestionId("-1").build());
        assert StringUtils.isEmpty(question.getQuestionId());
    }
}
