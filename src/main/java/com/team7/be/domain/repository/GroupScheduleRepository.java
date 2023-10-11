package com.team7.be.domain.repository;

import com.team7.be.domain.entity.GroupSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GroupScheduleRepository extends JpaRepository<GroupSchedule,Long> {
    Optional<GroupSchedule> findByGroupScheduleId(@Param("groupScheduleId")Long groupScheduleId);

//    Optional<GroupSchedule> findByUserGroupId(Long userGroupId);

//    Optional<GroupSchedule> findByGroupScheduleIdAndUserGroupId(Long groupScheduleId,Long userGroupId);

}
