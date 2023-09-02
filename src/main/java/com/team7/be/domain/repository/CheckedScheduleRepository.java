package com.team7.be.domain.repository;

import com.team7.be.domain.entity.schedule.CheckedSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckedScheduleRepository extends JpaRepository<CheckedSchedule,Long> {

    List<CheckedSchedule> findByCheckedScheduleIdAndGroupId(@Param("checkedScheduleId")Long scheduleId, @Param("groupId")Long groupId);

    List<CheckedSchedule> findByGroupId(@Param("groupId")Long groupId);

    List<CheckedSchedule> findByCheckedScheduleId(@Param("checkedScheduleId")Long scheduleId);

}
