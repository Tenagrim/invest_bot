package com.tenagrim.telegram.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "APP_AUTHORITY")
public class AppAuthority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "AUTHORITY")
    String authority;
}
