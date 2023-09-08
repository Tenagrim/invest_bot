package com.tenagrim.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "NODE_POSITION")
public class NodePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "X")
    Float x;

    @Column(name = "Y")
    Float y;

    @JsonIgnore // todo: use dto
    @OneToOne(mappedBy = "nodePosition")
    private Chapter chapter;
}
