package com.sparrows.user.admin.adapter.repository;

import com.sparrows.user.admin.model.entity.FailureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailureRepository extends JpaRepository<FailureEntity, Long> {
}
