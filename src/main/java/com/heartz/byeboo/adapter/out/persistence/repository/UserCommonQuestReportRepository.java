package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserCommonQuestReportRepository extends JpaRepository<ReportEntity, Long> {

    @Modifying
    @Query("DELETE FROM ReportEntity r " +
            "WHERE r.reportTargetType = com.heartz.byeboo.domain.type.EReportTargetType.COMMON_QUEST " +
            "AND r.targetId IN " +
            "(SELECT uq.id FROM UserCommonQuestEntity uq WHERE uq.userId = :userId)")
    void deleteAllByReportedUserId(@Param("userId") Long userId);
}
