package com.tenagrim.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Table(name = "BOT_CONFIG_VERSION")
public class BotConfigVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "botConfigVersion")
//    @JoinColumn(name = "BOT_CONFIG_VERSION_ID", referencedColumnName = "ID")
    BotConfig botConfig;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "BOT_CONFIG_VERSION_ID", referencedColumnName = "ID")
    Set<DataVersion> dataVersions;
}
