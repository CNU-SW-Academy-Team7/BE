package com.team7.be.domain.service;
import com.team7.be.domain.controller.UserGroupController;
import com.team7.be.domain.controller.response.userGroup.GetGroupResponse;
import com.team7.be.domain.entity.GroupSchedule;
import com.team7.be.domain.entity.UserGroup;
import com.team7.be.domain.repository.GroupScheduleRepository;
import com.team7.be.domain.repository.MemberRepository;
import com.team7.be.domain.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class UserGroupService {
    private final UserGroupRepository userGroupRepository;
    private final GroupScheduleRepository groupScheduleRepository;
    private final MemberRepository memberRepository;

    //새로운 그룹 추가
    @Transactional
    public Long saveGroup(String groupName){
        UserGroup userGroup = UserGroup.builder()
                .userGroupName(groupName).build();
        return userGroupRepository.save(userGroup).getUserGroupId();
    }
    //기존 그룹, 그룹 id로 삭제



    //TODO : group에 join 하여 userGroup에 해당 사용자에 대한 정보를 저장하는 메소드 필요
    public String enrollGroup(Long userId,Long userGroupId){
        UserGroup userGroup = UserGroup.builder()
                .userGroupId(userGroupId)
                .userGroupName(userGroupRepository.findUserGroupByUserGroupId(userGroupId).getUserGroupName())
                .userId(userId)
                .build();

        return userGroup.getUserGroupName();
    }


    public List<GetGroupResponse> getGroupList(Long userId) {
        List<GetGroupResponse> groupList=new ArrayList<>();

        List<UserGroup> userGroups = userGroupRepository.findByUserId(userId); // user가 속해있는 group을 모두 불러옴


        if (!userGroups.isEmpty()) {
            for(UserGroup userGroup:userGroups){

                List<UserGroup> groupInfoList = userGroupRepository.findByUserGroupName(userGroup.getUserGroupName());
                List<String> groupMemberList = new ArrayList<>();

                for(UserGroup info : groupInfoList){
                    groupMemberList.add(memberRepository.findByUserId(info.getUserId()).get().getUserName());
                }

                groupList.add(GetGroupResponse.builder()
                        .groupName(userGroup.getUserGroupName())
                        .memberList(groupMemberList)
                        .build());
            }
            return groupList;
        }
        else{
            return null;
        }
    }



}