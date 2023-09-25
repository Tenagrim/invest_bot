package com.tenagrim.telegram.model;

import com.tenagrim.telegram.model.chapter.ChapterMark;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Table(name = "BOT_CONFIG")
public class BotConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "SYSNAME")
    String sysName;

    @Column(name = "NAME")
    String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURRENT_VERSION_ID", referencedColumnName = "ID")
    DataVersion currentVersion;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOT_CONFIG_VERSION_ID", referencedColumnName = "ID")
    BotConfigVersion botConfigVersion;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "BOT_CONFIG_ID", referencedColumnName = "ID")
    Set<ChapterMark> chapterMarks;

    public void setCurrentVersion(DataVersion currentVersion) {
        this.currentVersion = currentVersion;
    }
}
