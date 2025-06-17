package com.sparrows.user.user.port.out;

import com.sparrows.user.user.model.dto.SchoolValidateRequest;

public interface SchoolPort {
    boolean isSameLocationWithSchoolId(SchoolValidateRequest request);
}
