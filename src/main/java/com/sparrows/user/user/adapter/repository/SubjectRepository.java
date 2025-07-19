package com.sparrows.user.user.adapter.repository;

import com.sparrows.user.user.model.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    List<SubjectEntity> findByUserIdAndTerm(Long userId, Integer term);
}
