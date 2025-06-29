package com.sparrows.user.user.port.out;


import com.sparrows.user.user.model.enums.UserType;

public interface UserEventPort {
    void publishUserCreatedEvent(Long userId, String nickname, Integer schoolId, UserType userType);
}
