package org.example.grpc.question;

import com.example.grpc.Question;
import com.example.grpc.QuestionId;
import com.example.grpc.Theme;
import org.apache.commons.lang3.StringUtils;
import org.example.db.QuestionRepository;
import org.example.exceptions.QuizExceptions;
import org.example.grpc.logic.question.QuestionLogic;
import org.example.grpc.logic.question.QuestionLogicImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class QuestionLogicTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    QuestionRepository repository;

    QuestionLogic questionLogic;
    @Before
    public void beforeClass() {
        questionLogic = new QuestionLogicImpl(repository);
    }

    @Test
    public void getRandomQuestionTest() {
        Question question = questionLogic.getRandomQuestion();
        System.out.println(question);
        assertTrue(StringUtils.isNotEmpty(question.getQuestionId()));
    }

    @Test
    public void getQuestionByIdTest() {
        Question question = questionLogic.getQuestionById(QuestionId.newBuilder().setQuestionId("1").build());
        System.out.println(question.getQuestion());
        assertTrue(StringUtils.isNotEmpty(question.getQuestionId()));
        question = questionLogic.getQuestionById(QuestionId.newBuilder().setQuestionId("-1").build());
        assertTrue(StringUtils.isEmpty(question.getQuestionId()));
    }

    @Test
    public void getQuestionByThemeTest() {
        Question question = questionLogic.getQuestionByTheme(Theme.newBuilder().setThemeId("1").build());
        System.out.println(question);
        assertTrue(StringUtils.isNotEmpty(question.getQuestionId()));
        question = questionLogic.getQuestionByTheme(Theme.newBuilder().setThemeId("-1").build());
        System.out.println(question);
        assertTrue(StringUtils.isEmpty(question.getQuestionId()));
    }

    @Test
    public void testCreateQuestion() {
        QuestionId questionId = questionLogic.createQuestion(Question.newBuilder()
                .setQuestionId("3")
                .setQuestion("Which actor won Best Actor Oscars for Philadelphia (1993) and Forrest Gump (1994)? (Tom Hanks)")
                .setThemeId("3")
                .setAnswerId("3")
                .build());
        String questionIdStr = questionId.getQuestionId();
        System.out.println(questionIdStr);
        StringUtils.isNotEmpty(questionIdStr);
        Question question = questionLogic.getQuestionById(QuestionId.newBuilder().setQuestionId(questionIdStr).build());
        System.out.println(question.getQuestion());
        assertEquals("3", question.getQuestionId());
        assertEquals("Which actor won Best Actor Oscars for Philadelphia (1993) and Forrest Gump (1994)? (Tom Hanks)",
                question.getQuestion());
    }

    @Test
    public void testThrowExceptionWhenCreateExistingQuestion() {
        assertThrows(QuizExceptions.class, () ->{
            questionLogic.createQuestion(Question.newBuilder()
                    .setQuestionId("1")
                    .setQuestion("Which actor won Best Actor Oscars for Philadelphia (1993) and Forrest Gump (1994)? (Tom Hanks)")
                    .setThemeId("1")
                    .setAnswerId("3")
                    .build());
        });
    }
}
