package com.sparrows.user.user.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectRequestDto {

    private Long userId;
    private String name;
    private Integer startTime;
    private Integer endTime;
    private Integer term;
}
