package com.sparrows.user.security.model;

import com.sparrows.user.user.model.enums.SchoolRank;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SchoolGrantedAuthority implements GrantedAuthority {

    int schoolId;
    SchoolRank schoolRank;
    @Override
    public String getAuthority() {
        return schoolId+"-"+schoolRank.toString();
    }
}
