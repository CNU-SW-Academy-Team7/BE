package com.team7.be.domain.repository;

import com.team7.be.domain.entity.Member;
import com.team7.be.domain.entity.Schedule;
import com.team7.be.domain.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    Optional<Schedule> findByScheduleId(@Param("scheduleId")Long scheduleId);
}
