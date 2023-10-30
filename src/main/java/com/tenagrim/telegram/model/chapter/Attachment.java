package com.tenagrim.telegram.model.chapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "ATTACHMENT")
@Setter
public class Attachment {
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
