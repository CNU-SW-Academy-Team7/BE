package com.team7.be.domain.repository;

import com.team7.be.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByGroupId(@Param("groupId") Long groupId);
    Optional<Schedule> findByGroupIdAndMemberId(@Param("groupId") Long groupId, @Param("MemberId") Long memberId);

    List<Schedule> findByGroupIdAndSelectedStartDateIsAfterAndSelectedEndDateIsBefore(
            @Param("groupId") Long groupId,
            @Param("selectedStartDate") LocalDateTime selectedStartDate,
            @Param("selectedEndDate") LocalDateTime selectedEndDate
    );

    // 받은 날짜로부터 7일 후까지의 스케줄을 받아오는 메소드, selectedEndDate를 현재 날짜로부터 7일 후로 설정해야함.
    List<Schedule> findByGroupIdAndSelectedStartDateIsAfterAndSelectedEndDateIsBeforeOrSelectedEndDateIsNull(
            @Param("groupId") Long groupId,
            @Param("selectedStartDate") LocalDateTime selectedStartDate,
            @Param("selectedEndDate") LocalDateTime selectedEndDate
    );
}
