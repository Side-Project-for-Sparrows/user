package com.sparrows.user.user.port.out;

import com.sparrows.user.user.model.entity.SubjectEntity;

import java.util.List;
import java.util.Optional;

public interface SubjectRepositoryPort {
    SubjectEntity save(SubjectEntity entity);
    Optional<SubjectEntity> findById(Long id);
    void deleteById(Long id);
    List<SubjectEntity> findByUserIdAndTerm(Long userId, Integer term);
}
