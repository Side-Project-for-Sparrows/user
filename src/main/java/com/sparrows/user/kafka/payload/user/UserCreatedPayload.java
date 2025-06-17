package com.sparrows.user.kafka.payload.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparrows.user.user.model.enums.UserType;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreatedPayload{
    public long userId;
    public int schoolId;
    public UserType userType;
}
