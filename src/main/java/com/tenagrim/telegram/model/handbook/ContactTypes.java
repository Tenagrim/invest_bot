package com.tenagrim.telegram.model.handbook;

public enum ContactTypes {
    MOBILE_PHONE(1L);
    private Long id;

    ContactTypes(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
