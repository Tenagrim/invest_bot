package com.tenagrim.telegram.model.chapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tenagrim.telegram.model.chapter.Chapter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "NODE_POSITION")
@Setter // todo: maybe make immutable
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
