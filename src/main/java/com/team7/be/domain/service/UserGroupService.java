package com.team7.be.domain.service;
import com.team7.be.domain.entity.UserGroup;
import com.team7.be.domain.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class UserGroupService {
    private final UserGroupRepository userGroupRepository;

    //새로운 그룹 추가
    @Transactional
    public Long saveGroup(String groupName){
        UserGroup userGroup = UserGroup.builder()
                .groupName(groupName).build();
        return userGroupRepository.save(userGroup).getGroupId();
    }
    //기존 그룹, 그룹 id로 삭제
    @Transactional
    public void deleteGroup(Long GroupId){
        userGroupRepository.deleteByGroupId(GroupId);
    }
    //그룹 id로 탐색

    public Optional<UserGroup> findGroupById(Long GroupId){
        return userGroupRepository.findByGroupId(GroupId);
    }
    //그룹 이름으로 탐색

    public Optional<UserGroup> findGroupByName(String GroupName){
        return userGroupRepository.findByGroupName(GroupName);
    }
}