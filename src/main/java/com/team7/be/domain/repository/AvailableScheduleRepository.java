package com.team7.be.domain.repository;

import com.team7.be.domain.entity.availableSchedule.AvailableSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableScheduleRepository extends JpaRepository<AvailableSchedule,Long> {

    List<AvailableSchedule> findByGroupScheduleIdAndUserGroupId(@Param("groupScheduleId")Long groupScheduleId,@Param("userGroupId")Long userGroupId);
    List<AvailableSchedule> findAvailableScheduleByUserId(@Param("userId")Long userId);

    Optional<AvailableSchedule> findFirstByUserId(@Param("userId")Long userId);

    Optional<AvailableSchedule> findByAvailableScheduleId(@Param("availableScheduleId")Long availableScheduleId);

    Optional<AvailableSchedule> findFirstByAvailableScheduleIdAndUserGroupId(@Param("availableScheduleId")Long availableScheduleId,@Param("userGroupId")Long userGroupId);

}
