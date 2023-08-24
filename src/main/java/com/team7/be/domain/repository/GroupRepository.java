package com.team7.be.domain.repository;
import com.team7.be.domain.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    //JAP repository를 사용
    // 그냥 사용하는 것 이 아닌 build.gradle의 dependency에 선언해서 사용가능.

    //그룹 식별키로 그룹을 찾는 부분. 인자로 Long형 값을 받음.
    Optional<Group> findByGroupId(@Param("groupId")Long groupId);
    //그룹 식별키로 그룹을 제거하는 부분. 인자로 Long형 값을 받음.
    void deleteByGroupId(@Param("groupId")Long groupId);

    //그룹 이름으로 그룹을 찿는 부분 그룹이름은 unique하게 함.
    Optional<Group> findByGroupName(@Param("groupName") String groupName);


}
