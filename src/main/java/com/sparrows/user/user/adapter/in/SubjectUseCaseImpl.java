package com.sparrows.user.user.adapter.in;

import com.sparrows.user.user.exception.handling.AccessDeniedException;
import com.sparrows.user.user.exception.handling.SubjectNotFoundException;
import com.sparrows.user.user.exception.handling.SubjectTimeOverlapException;
import com.sparrows.user.user.exception.handling.UserRelationNotFoundException;
import com.sparrows.user.user.model.dto.SubjectRequestDto;
import com.sparrows.user.user.model.dto.SubjectResponseDto;
import com.sparrows.user.user.model.entity.SubjectEntity;
import com.sparrows.user.user.port.in.SubjectUseCase;
import com.sparrows.user.user.port.out.SubjectRepositoryPort;
import com.sparrows.user.user.port.out.UserRelationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectUseCaseImpl implements SubjectUseCase {

    private final SubjectRepositoryPort subjectRepositoryPort;
    private final UserRelationPort userRelationPort;

    @Override
    public SubjectResponseDto createSubject(SubjectRequestDto request) {
        validateTimeOverlap(request.getUserId(), request.getTerm(), request.getStartTime(), request.getEndTime(), null);

        SubjectEntity entity = SubjectEntity.builder()
                .userId(request.getUserId())
                .name(request.getName())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .term(request.getTerm())
                .build();

        return SubjectResponseDto.fromEntity(subjectRepositoryPort.save(entity));
    }

    @Override
    public SubjectResponseDto updateSubject(Long subjectId, Long requesterId, SubjectRequestDto request) {
        SubjectEntity subject = subjectRepositoryPort.findById(subjectId)
                .orElseThrow(SubjectNotFoundException::new);
        if (!subject.getUserId().equals(requesterId)) throw new AccessDeniedException();

        validateTimeOverlap(subject.getUserId(), request.getTerm(), request.getStartTime(), request.getEndTime(), subjectId);

        subject.update(request);
        SubjectEntity saved = subjectRepositoryPort.save(subject);
        return SubjectResponseDto.fromEntity(saved);
    }

    @Override
    public void deleteSubject(Long subjectId, Long requesterId) {
        SubjectEntity subject = subjectRepositoryPort.findById(subjectId)
                .orElseThrow(SubjectNotFoundException::new);
        if (!subject.getUserId().equals(requesterId)) throw new AccessDeniedException();

        subjectRepositoryPort.deleteById(subjectId);
    }

    @Override
    public List<SubjectResponseDto> getSubjectsByUserAndTerm(Long requesterId, Long userId, Integer term) {
        // 본인이거나, 친구 관계인지 확인
        if (!requesterId.equals(userId) && !userRelationPort.existsByUsers(requesterId, userId)) {
            throw new UserRelationNotFoundException();
        }

        return subjectRepositoryPort.findByUserIdAndTerm(userId, term)
                .stream()
                .map(SubjectResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 특정 시간에 겹치는 과목 존재하는지 판단해서 에러던짐
    private void validateTimeOverlap(Long userId, Integer term, Integer startTime, Integer endTime, Long excludeId) {
        List<SubjectEntity> subjects = subjectRepositoryPort.findByUserIdAndTerm(userId, term);

        for (SubjectEntity subject : subjects) {
            // 수정일 경우, 자기 자신은 제외
            if (subject.getId().equals(excludeId)) continue;

            if (startTime < subject.getEndTime() && subject.getStartTime() < endTime) {
                throw new SubjectTimeOverlapException();
            }
        }
    }

}
