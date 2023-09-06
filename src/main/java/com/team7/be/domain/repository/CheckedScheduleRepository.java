package com.team7.be.domain.repository;

import com.team7.be.domain.entity.schedule.CheckedSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CheckedScheduleRepository extends JpaRepository<CheckedSchedule,Long> {

    List<CheckedSchedule> findByCheckedScheduleIdAndGroupId(@Param("checkedScheduleId")Long scheduleId, @Param("groupId")Long groupId);

    List<CheckedSchedule> findByGroupId(@Param("groupId")Long groupId);

    List<CheckedSchedule> findByCheckedScheduleId(@Param("checkedScheduleId")Long scheduleId);

    @Query("select s from CheckedSchedule s where s.selectedStartTime >= :selectedStartTime and s.selectedEndTime <= :selectedEndTime")
    List<CheckedSchedule> getAllOneWeekSchedule(@Param("selectedStartTime")LocalDateTime selectedStartTime,@Param("selectedEndTime")LocalDateTime selectedEndTime);

    @Query("select s from CheckedSchedule s where s.selectedStartTime >= :selectedStartTime and s.selectedEndTime <= :selectedEndTime and s.groupId =:groupId")
    List<CheckedSchedule> getGroupOneWeekSchedule(@Param("groupId")Long groupId,@Param("selectedStartTime")LocalDateTime selectedStartTime,@Param("selectedEndTime")LocalDateTime selectedEndTime);
}
