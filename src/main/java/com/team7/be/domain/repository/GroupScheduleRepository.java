package com.team7.be.domain.repository;

import com.team7.be.domain.entity.GroupSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GroupScheduleRepository extends JpaRepository<GroupSchedule,Long> {
    Optional<GroupSchedule> findByGroupScheduleId(@Param("groupScheduleId")Long groupScheduleId);

    Optional<GroupSchedule> findByUserGroupId(@Param("userGroupId")Long userGroupId);



    List<GroupSchedule> findByUserId(@Param("userId")Long userId);


}
