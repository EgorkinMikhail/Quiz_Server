package org.example.grpc.theme;

import com.example.grpc.Theme;
import com.example.grpc.ThemeId;
import org.apache.commons.lang3.StringUtils;
import org.example.db.ThemeRepository;
import org.example.grpc.logic.theme.ThemeLogic;
import org.example.grpc.logic.theme.ThemeLogicImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ThemeLogicTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ThemeRepository themeRepository;

    ThemeLogic themeLogic;

    @Before
    public void beforeClass() {
        themeLogic = new ThemeLogicImpl(themeRepository);
    }

    @Test
    public void testCreateTheme() {
        ThemeId newThemeId = themeLogic.createTheme(Theme.newBuilder()
                .setThemeId("4")
                .setTheme("Music")
                .build());
        System.out.println(newThemeId.getThemeId());
        assertEquals("4", newThemeId.getThemeId());
        Theme theme = themeLogic.getTheme(newThemeId);
        System.out.println(theme.getTheme());
        assertEquals("Music", theme.getTheme());
    }

    @Test
    public void testGetTheme() {
        ThemeId themeId = ThemeId.newBuilder().setThemeId("1").build();
        Theme theme = themeLogic.getTheme(themeId);
        assertTrue(StringUtils.isNotEmpty(theme.getThemeId()));
        assertTrue(StringUtils.isNotEmpty(theme.getTheme()));
    }

    @Test
    public void testGetEmptyTheme() {
        ThemeId themeId = ThemeId.newBuilder().setThemeId("-1").build();
        Theme theme = themeLogic.getTheme(themeId);
        assertTrue(StringUtils.isEmpty(theme.getThemeId()));
        assertTrue(StringUtils.isEmpty(theme.getTheme()));
    }
}
