package com.sparrows.user.user.adapter.out;

import com.sparrows.user.user.feignclient.UserIndexClient;
import com.sparrows.user.user.model.dto.SchoolValidateRequest;
import com.sparrows.user.user.port.out.SchoolPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignSchoolAdapter implements SchoolPort {
    private final UserIndexClient userIndexClient;

    @Override
    public boolean isSameLocationWithSchoolId(SchoolValidateRequest request) {
        return userIndexClient.validateSchool(request);
    }
}
