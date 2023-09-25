package com.tenagrim.telegram.model.chapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "CHAPTER_MARK")
public class ChapterMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "KEY")
    private Long key;

    @Column(name = "NAME")
    String name;

    @JsonIgnore // todo: use dto
    @ManyToOne
    @JoinColumn(name = "BOT_CONFIG_ID", nullable = false)
    private Chapter chapter;
}
