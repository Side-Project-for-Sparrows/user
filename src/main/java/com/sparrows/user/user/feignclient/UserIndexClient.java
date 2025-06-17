package com.sparrows.user.user.feignclient;

import com.sparrows.user.user.model.dto.SchoolValidateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-index-service", url = "${user.service.url}")
public interface UserIndexClient {
    @PostMapping("/school/validate")
    boolean validateSchool(@RequestBody SchoolValidateRequest request);
}
