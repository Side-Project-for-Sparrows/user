package com.sparrows.user.user.port.in;

import com.sparrows.user.user.model.dto.SubjectRequestDto;
import com.sparrows.user.user.model.dto.SubjectResponseDto;

import java.util.List;

public interface SubjectUseCase {
    SubjectResponseDto createSubject(SubjectRequestDto request);
    SubjectResponseDto updateSubject(Long subjectId, Long requesterId, SubjectRequestDto request);
    void deleteSubject(Long subjectId, Long requesterId);
    List<SubjectResponseDto> getSubjectsByUserAndTerm(Long requesterId, Long userId, Integer term);
}
