package org.example.grpc.service;

import com.example.grpc.Theme;
import com.example.grpc.ThemeId;

public interface ThemeService {
    ThemeId createTheme (Theme theme);
    Theme getTheme (ThemeId themeId);
}
