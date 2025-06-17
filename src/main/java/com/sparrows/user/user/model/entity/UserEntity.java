package com.sparrows.user.user.model.entity;

import com.sparrows.user.common.model.BaseEntity;
import com.sparrows.user.user.model.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String loginPassword;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    private int point;

    @Column(name = "is_valid", nullable = false)
    @ColumnDefault("true")
    private boolean isValid;
}
