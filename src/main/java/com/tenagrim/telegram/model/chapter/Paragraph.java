package com.tenagrim.telegram.model.chapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "PARAGRAPH")
public class Paragraph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "TEXT")
    String text;

    @Column(name = "NOTE")
    String note;

    @Column(name = "PLACEMENT")
    Integer placement;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PARAGRAPH_ID", referencedColumnName = "ID")
    Set<ParagraphButton> paragraphButtons;

    @JsonIgnore // todo: use dto
    @ManyToOne
    @JoinColumn(name = "CHAPTER_ID", nullable = false)
    private Chapter chapter;
}
