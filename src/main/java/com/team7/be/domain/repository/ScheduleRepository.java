package com.team7.be.domain.repository;

import com.team7.be.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByGroupId(@Param("groupId") Long groupId);
    Optional<Schedule> findByGroupIdAndMemberId(@Param("groupId") Long groupId, @Param("MemberId") Long memberId);
}
