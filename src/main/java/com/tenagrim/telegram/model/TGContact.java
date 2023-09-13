package com.tenagrim.telegram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Table(name = "TG_CONTACT")
public class TGContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "CONTACT_TYPE_ID")
    Long contactTypeId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    TGUser user;

    @Column(name = "VALUE")
    String value;

    @Column(name = "CREATE_DATE", insertable = false)
    ZonedDateTime createDate;
}
