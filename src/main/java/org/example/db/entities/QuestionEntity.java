package org.example.db.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;
@Data
@Entity
@Table(name = "Question")
public class QuestionEntity {
    @Id
    @Column(unique = true, nullable = false, name = "question_id")
    @JsonProperty("question_id")
    private String questionId;
    @Column(name = "question")
    @JsonProperty("question")
    private String question;
    @Column(name = "theme_id")
    @JsonProperty("theme_id")
    private String themeId;
    @Column(name = "answer_id")
    @JsonProperty("answer_id")
    private String answerId;
}
