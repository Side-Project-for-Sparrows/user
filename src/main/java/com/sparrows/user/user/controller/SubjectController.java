package com.sparrows.user.user.controller;

import com.sparrows.user.user.model.dto.SubjectRequestDto;
import com.sparrows.user.user.model.dto.SubjectResponseDto;
import com.sparrows.user.user.port.in.SubjectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectUseCase subjectUseCase;

    @PostMapping
    public ResponseEntity<SubjectResponseDto> createSubject(@RequestBody SubjectRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectUseCase.createSubject(request));
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<SubjectResponseDto> updateSubject(
            @RequestHeader("X-Requester-Id") Long requesterId,
            @PathVariable Long subjectId,
            @RequestBody SubjectRequestDto request) {

        return ResponseEntity.ok(subjectUseCase.updateSubject(subjectId, requesterId, request));
    }


    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubject(
            @RequestHeader("X-Requester-Id") Long requesterId,
            @PathVariable Long subjectId) {

        subjectUseCase.deleteSubject(subjectId, requesterId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/list")
    public ResponseEntity<List<SubjectResponseDto>> getSubjects(@RequestParam Long userId, @RequestParam Integer term) {
        return ResponseEntity.ok(subjectUseCase.getSubjectsByUserAndTerm(userId, term));
    }
}
