package com.tenagrim.telegram.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CHAPTER")
@Getter
public class Chapter {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "TEXT")
    String text;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "CHAPTER_ID", referencedColumnName = "ID")
    Set<ChapterButton> chapterButtons;
}
