package org.example.db.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Answer")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private Integer answerId;

    @Column(name = "answer", nullable = false)
    private String answer;
}
