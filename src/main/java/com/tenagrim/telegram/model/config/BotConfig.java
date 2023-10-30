package com.tenagrim.telegram.model.config;

import com.tenagrim.telegram.model.DataVersion;
import com.tenagrim.telegram.model.chapter.ChapterMark;
import lombok.Getter;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "BOT_CONFIG_ID", referencedColumnName = "ID")
    Set<BotConfigPropertyValue> configProperties;



    @Transient
    private Map<String, Object> botConfigPropertiesCache;

    public Map<String, Object> getBotConfigProperties(){ // TODO: should not be in entity class
        if (botConfigPropertiesCache == null){
            botConfigPropertiesCache = configProperties.stream()
                    .collect(Collectors.toMap(el-> el.getProperty().getSysName(), BotConfigPropertyValue::getValue));
        }
        return botConfigPropertiesCache;
    }
    public void setCurrentVersion(DataVersion currentVersion) {
        this.currentVersion = currentVersion;
    }

    public Long getId() {
        return id;
    }

    public String getSysName() {
        return sysName;
    }

    public String getName() {
        return name;
    }

    public DataVersion getCurrentVersion() {
        return currentVersion;
    }

    public BotConfigVersion getBotConfigVersion() {
        return botConfigVersion;
    }

    public Set<ChapterMark> getChapterMarks() {
        return chapterMarks;
    }

    public Set<BotConfigPropertyValue> getConfigProperties() {
        return configProperties;
    }
}
