package org.example.grpc.logic.theme;

import com.example.grpc.Theme;
import com.example.grpc.ThemeId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.db.ThemeRepository;
import org.example.db.entities.ThemeEntity;
import org.example.exceptions.QuizExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ThemeLogicImpl implements ThemeLogic {

    private final ThemeRepository themeRepository;

    @Override
    public ThemeId createTheme(Theme theme) {
        if (themeRepository.findById(theme.getThemeId()).isPresent()) {
            throw new QuizExceptions("Theme ID is already in DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ThemeEntity themeEntity = new ThemeEntity();
        themeEntity.setThemeId(theme.getThemeId());
        themeEntity.setTheme(theme.getTheme());
        return ThemeId.newBuilder()
                .setThemeId(themeRepository.save(themeEntity).getThemeId())
                .build();
    }

    @Override
    public Theme getTheme(ThemeId themeId) {
        Optional<ThemeEntity> themeEntityById = themeRepository.findById(themeId.getThemeId());
        return themeEntityById.map(themeEntity -> Theme.newBuilder()
                .setThemeId(themeEntity.getThemeId())
                .setTheme(themeEntity.getTheme()).build())
                .orElse(Theme.newBuilder().build());
    }
}
