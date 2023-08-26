package com.team7.be.domain.service;
import com.team7.be.domain.entity.Group;
import com.team7.be.domain.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class GroupService {
    private final GroupRepository groupRepository;

    //새로운 그룹 추가
    @Transactional
    public Optional<Group> saveGroup(Group group){
        return Optional.of(groupRepository.save(group));
    }
    //기존 그룹, 그룹 id로 삭제
    @Transactional
    public void deleteGroup(Long GroupId){
        groupRepository.deleteByGroupId(GroupId);
    }
    //그룹 id로 탐색
    @Transactional
    public Optional<Group> findGroupById(Long GroupId){
        return groupRepository.findByGroupId(GroupId);
    }
    //그룹 이름으로 탐색
    @Transactional
    public Optional<Group> findGroupByName(String GroupName){
        return groupRepository.findByGroupName(GroupName);
    }
}