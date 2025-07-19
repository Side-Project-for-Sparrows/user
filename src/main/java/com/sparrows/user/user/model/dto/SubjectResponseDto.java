package com.sparrows.user.user.model.dto;

import com.sparrows.user.user.model.entity.SubjectEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectResponseDto {

    private Long id;
    private Long userId;
    private String name;
    private Integer startTime;
    private Integer endTime;
    private Integer term;

    public static SubjectResponseDto fromEntity(SubjectEntity entity) {
        return SubjectResponseDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .name(entity.getName())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .term(entity.getTerm())
                .build();
    }
}
