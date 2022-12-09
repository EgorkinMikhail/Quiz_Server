package org.example.db.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Theme")
public class ThemeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_id", nullable = false)
    private Integer themeId;

    @Column(name = "theme", nullable = false)
    private String theme;
}
