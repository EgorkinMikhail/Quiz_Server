package org.example.db;

import lombok.NonNull;
import org.example.db.entities.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, String> {
    @Transactional(readOnly = true)
    Optional<AnswerEntity> findById(@NonNull String key);

    @Transactional
    AnswerEntity save(@NonNull AnswerEntity answerEntity);
}
