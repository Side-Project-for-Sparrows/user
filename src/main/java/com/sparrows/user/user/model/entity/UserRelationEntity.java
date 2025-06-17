package com.sparrows.user.user.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "user_relation",
        indexes = { @Index(name = "idx_user1", columnList = "user1") }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserRelationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user1", nullable = false)
    private Long user1;

    @Column(name = "user2", nullable = false)
    private Long user2;

    @Column(nullable = false)
    private String nickname;
}
