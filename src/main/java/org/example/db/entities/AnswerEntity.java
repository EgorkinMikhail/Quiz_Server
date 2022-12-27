package org.example.db.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Answer")
public class AnswerEntity {
    @Id
    @Column(name = "answer_id", nullable = false)
    private String answerId;

    @Column(name = "answer", nullable = false)
    private String answer;
}
