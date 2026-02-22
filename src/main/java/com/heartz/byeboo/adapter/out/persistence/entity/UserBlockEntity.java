package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.type.EReportStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "user_blocks",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_blocker_blocked",
                        columnNames = {"blocker_user_id", "blocked_user_id"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_blocked_user",
                        columnList = "blocked_user_id"
                )
        }
        )
public class UserBlockEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "blocker_user_id", nullable = false)
    private Long blockerUserId;

    @Column(name = "blocked_user_id", nullable = false)
    private Long blockedUserId;

    @Builder
    public UserBlockEntity(
            Long id,
            Long blockerUserId,
            Long blockedUserId){
        this.id = id;
        this.blockedUserId = blockedUserId;
        this.blockerUserId = blockerUserId;
    }

    public static UserBlockEntity create(
            Long blockerUserId,
            Long blockedUserId
    ) {
        return UserBlockEntity.builder()
                .blockerUserId(blockerUserId)
                .blockedUserId(blockedUserId)
                .build();
    }

}
