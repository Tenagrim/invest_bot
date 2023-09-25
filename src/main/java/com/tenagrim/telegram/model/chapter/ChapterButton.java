package com.tenagrim.telegram.model.chapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CHAPTER_BUTTON")
@Getter
@Setter
public class ChapterButton {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEXT")
    private String text;

//    @Column(name = "CHAPTER_ID")
//    Long chapterId;

    @Column(name = "TARGET_CHAPTER_ID")
    private Long targetChapterId;

    @Column(name = "PLACEMENT")
    private Integer placement;

    @JsonIgnore // todo: use dto
    @ManyToOne
    @JoinColumn(name = "CHAPTER_ID", nullable = false)
    private Chapter chapter;


    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
