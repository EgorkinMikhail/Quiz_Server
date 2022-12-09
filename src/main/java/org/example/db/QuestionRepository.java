package org.example.db;

import org.example.db.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, String> {

    @Transactional(readOnly = true)
    Optional<QuestionEntity> findById(String key);

    @Transactional(readOnly = true)
    List<QuestionEntity> findAll();

    @Transactional
    QuestionEntity save(QuestionEntity questionEntity);

}
