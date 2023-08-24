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

    @Transactional
    public Optional<Group> saveGroup(Group group){

        return Optional.of(groupRepository.save(group));
    }
    @Transactional
    public void deleteGroup(Long GroupId){
        groupRepository.deleteByGroupId(GroupId);
    }
    @Transactional
    public Optional<Group> findGroupById(Long GroupId){
        return groupRepository.findByGroupId(GroupId);
    }

    @Transactional
    public Optional<Group> findGroupByName(String GroupName){
        return groupRepository.findByGroupName(GroupName);
    }
}