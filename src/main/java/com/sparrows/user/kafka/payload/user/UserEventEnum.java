package com.sparrows.user.kafka.payload.user;

public enum UserEventEnum {
    UserCreated("UserCreated"),
    UserUpdated("UserUpdated"),
    UserDeleted("UserDeleted");

    String event;

    UserEventEnum(String event){
        this.event = event;
    }
}
