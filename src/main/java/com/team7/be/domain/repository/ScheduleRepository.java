package com.team7.be.domain.repository;

import com.team7.be.domain.entity.Schedule;
import com.team7.be.domain.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    //userId로 스케쥴 찾는 부분.
    Optional<Schedule> findByUserId(@Param("userId")Long userId);

    
}
