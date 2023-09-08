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

    @Column(name = "NOTE")
    String note;

    @Column(name = "TEXT")
    String text;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CHAPTER_ID", referencedColumnName = "ID")
    Set<ChapterButton> chapterButtons;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "CHAPTER_ID", referencedColumnName = "ID")
    Set<ChapterAttachement> chapterAttachements;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "NODE_POSITION_ID", referencedColumnName = "ID")
    NodePosition nodePosition;

    @Column(name = "CHAPTER_TYPE_ID")
    Long chapterTypeId;
}
