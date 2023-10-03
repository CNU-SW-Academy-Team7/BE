package com.team7.be.domain.repository;

import com.team7.be.domain.entity.Member;
import com.team7.be.domain.entity.Schedule;
import com.team7.be.domain.entity.UserGroup;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    //userId로 스케쥴 찾는 부분.
    List<Schedule> findByMemberId(@Param("memberId")Long userId);
    List<Schedule> findByMemberId_userId(Long id);
    List<Schedule> findByUserGroupId_GroupId(Long id);

}
