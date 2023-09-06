package com.tenagrim.telegram.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Command {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "TEXT")
    String text;

    @OneToOne
    @JoinColumn(name = "CHAPTER_ID", referencedColumnName = "ID")
    Chapter chapter;
}
