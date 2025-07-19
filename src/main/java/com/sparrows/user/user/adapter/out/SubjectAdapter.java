package com.sparrows.user.user.adapter.out;

import com.sparrows.user.user.adapter.repository.SubjectRepository;
import com.sparrows.user.user.model.entity.SubjectEntity;
import com.sparrows.user.user.port.out.SubjectRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubjectAdapter implements SubjectRepositoryPort {

    private final SubjectRepository repository;

    @Override
    public SubjectEntity save(SubjectEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<SubjectEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<SubjectEntity> findByUserIdAndTerm(Long userId, Integer term) {
        return repository.findByUserIdAndTerm(userId, term);
    }
}
