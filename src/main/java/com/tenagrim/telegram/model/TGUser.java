package com.tenagrim.telegram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "TG_USER")
public class TGUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "EXTERNAL_ID")
    Long externalId;
    @Column(name = "FIRST_NAME")
    String firstName;
    @Column(name = "USER_NAME")
    String userName;
    @Column(name = "LANGUAGE_CODE")
    String languageCode;
    @Column(name = "IS_BOT")
    Boolean isBot;
    @Column(name = "START_ARG")
    String startArg;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    Set<TGContact> contacts;

}
