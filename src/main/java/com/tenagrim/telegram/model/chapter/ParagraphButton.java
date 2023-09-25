package com.tenagrim.telegram.model.chapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PARAGRAPH_BUTTON")
@Getter
@Setter
public class ParagraphButton {
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
    @JoinColumn(name = "PARAGRAPH_ID", nullable = false)
    private Paragraph paragraph;
}
