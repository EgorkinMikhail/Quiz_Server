package org.example.db;

import org.example.db.entities.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeEntity, String> {

    @Transactional(readOnly = true)
    Optional<ThemeEntity> findById(String key);

    @Transactional
    ThemeEntity save(ThemeEntity themeEntity);
}
