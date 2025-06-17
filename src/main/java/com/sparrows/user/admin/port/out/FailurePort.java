package com.sparrows.user.admin.port.out;

import com.sparrows.user.admin.model.entity.FailureEntity;

public interface FailurePort {
    void save(FailureEntity report);
}
