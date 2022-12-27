package org.example.grpc.logic.theme;

import com.example.grpc.Theme;
import com.example.grpc.ThemeId;

public interface ThemeLogic {
    ThemeId createTheme(Theme theme);
    Theme getTheme(ThemeId themeId);
}
