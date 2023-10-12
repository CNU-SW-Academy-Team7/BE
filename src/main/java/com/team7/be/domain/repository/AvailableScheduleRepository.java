package com.team7.be.domain.repository;

import com.team7.be.domain.entity.availableSchedule.AvailableSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableScheduleRepository extends JpaRepository<AvailableSchedule,Long> {

    List<AvailableSchedule> findByAvailableScheduleIdAndUserGroupId(@Param("availableScheduleId")Long availableScheduleId, @Param("groupId")Long groupId);


    Optional<AvailableSchedule> findAvailableScheduleByUserId(@Param("userId")Long userId);




}
