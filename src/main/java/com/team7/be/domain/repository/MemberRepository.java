package com.team7.be.domain.repository;

import com.team7.be.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    //JAP repository를 사용
    // 그냥 사용하는 것 이 아닌 build.gradle의 dependency에 선언해서 사용가능.

    Optional<Member> findByUserId(@Param("userId")String userId);
    // memberId를 받아와 저장되어있는 레포지토리에서 Id를 가지고 있는 Member객체를 반환한다.
    // 이 repo에 대한 저장 및 탐색 등의 메소드 작업을 진행하는 곳은 service.MemberSerive
}
