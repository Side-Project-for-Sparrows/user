package com.sparrows.user.user.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "friend_request",
        uniqueConstraints = { // 중복 친구 요청을 아예 막아주 > @UniqueConstraint
                @UniqueConstraint(name = "uk_requester_recipient", columnNames = {"requesterId", "recipientId"})
        },
        indexes = {
                @Index(name = "idx_recipient", columnList = "recipientId")
        }
)
public class FriendRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long recipientId;

    @Column(nullable = false)
    private Long requesterId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime requestedAt;
}

