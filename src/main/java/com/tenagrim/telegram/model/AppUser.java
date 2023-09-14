package com.tenagrim.telegram.model;

import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Table(name = "APP_USER")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "USERNAME")
    String username;

    @Column(name = "PASSWORD")
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "APP_USER_AUTHORITY",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
    Set<AppAuthority> authorities;

    @Column(name = "LOCKED")
    Boolean locked;

    @Column(name = "ENABLED")
    Boolean enabled;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
