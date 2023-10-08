package com.team7.be.domain.service;
import com.team7.be.domain.controller.response.HomeResponse;
import com.team7.be.domain.controller.response.getGroupResponse;
import com.team7.be.domain.entity.Member;
import com.team7.be.domain.entity.Schedule;
import com.team7.be.domain.entity.UserGroup;
import com.team7.be.domain.repository.MemberRepository;
import com.team7.be.domain.repository.ScheduleRepository;
import com.team7.be.domain.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class UserGroupService {
    private final UserGroupRepository userGroupRepository;
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
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

    public List<getGroupResponse> getGroupList(Long userId) {
        List<getGroupResponse> groupList=new ArrayList<>();
        List<Schedule> schedule = scheduleRepository.findByMemberId_userId(userId);
        if (!schedule.isEmpty()) {
            for(Schedule s:schedule){
                var memberList=new HashSet<String>();
                var groupId=s.getUserGroupId().getGroupId();
                var groupName=s.getUserGroupId().getGroupName();
                var selectScheduleByGroup=scheduleRepository.findByUserGroupId_GroupId(groupId);
                for(Schedule getName:selectScheduleByGroup){
                    memberList.add(getName.getMemberId().getUserName());
                }
                groupList.add(getGroupResponse.builder()
                        .groupName(groupName)
                        .memberList(memberList.stream().toList())
                        .build());
            }
            return groupList;
        }
        else{
            return null;
        }
    }
}