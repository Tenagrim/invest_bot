package com.tenagrim.telegram.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@DynamicInsert
@Getter
@Setter
@Table(name = "DATA_VERSION")
public class DataVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "CREATE_DATE")
    ZonedDateTime createDate;

    @Column(name = "UPDATE_DATE")
    ZonedDateTime updateDate;

    @Column(name = "NOTE")
    String note;

    @Column(name = "BOT_CONFIG_VERSION_ID")
    Long botConfigVersionId;



    //    @OneToOne
//    @JoinColumn(name = "CURRENT_VERSION_ID", referencedColumnName = "ID")
//    BotConfig botConfig;

}
