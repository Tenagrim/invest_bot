package com.tenagrim.telegram.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AmoCrmGrantType {
    AUTHORIZATION_CODE("authorization_code"),
    REFRESH_TOKEN("refresh_token")
    ;

    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
