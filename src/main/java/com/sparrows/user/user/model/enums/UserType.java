package com.sparrows.user.user.model.enums;

import lombok.Getter;

@Getter
public enum UserType {
    ADMIN("admin"),
    OFFICIAL("official"),
    OUTSIDER("outsider");

    final String type;
    private UserType(String type){
        this.type = type;
    }

    public String toString(){
        return this.type;
    }
}
