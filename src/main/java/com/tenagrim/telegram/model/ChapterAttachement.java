package com.tenagrim.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "CHAPTER_ATTACHEMENT")
@Setter
public class ChapterAttachement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "FILENAME")
    String filename;

    @JsonIgnore // todo: use dto
    @ManyToOne
    @JoinColumn(name = "CHAPTER_ID", nullable = false)
    private Chapter chapter;

}
