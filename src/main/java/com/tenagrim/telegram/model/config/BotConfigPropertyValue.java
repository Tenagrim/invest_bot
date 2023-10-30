package com.tenagrim.telegram.model.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tenagrim.telegram.model.chapter.Chapter;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "BOT_CONFIG_PROPERTIES_VALUES")
public class BotConfigPropertyValue {
    @Id
    Long id;

    @Column(name = "VALUE")
    String value;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROPERTY_ID", referencedColumnName = "ID")
    private BotConfigProperty property;

    @JsonIgnore // todo: use dto
    @ManyToOne
    @JoinColumn(name = "BOT_CONFIG_ID", nullable = false)
    private Chapter chapter;
}
