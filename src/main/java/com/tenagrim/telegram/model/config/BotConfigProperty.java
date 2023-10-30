package com.tenagrim.telegram.model.config;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "BOT_CONFIG_PROPERTIES")
public class BotConfigProperty {
    @Id
    Long id;

    @Column(name = "SYSNAME")
    String sysName;

    @Column(name = "DESCRIPTION")
    String description;
}
