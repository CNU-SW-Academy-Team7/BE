package com.team7.be.domain.service;

import com.team7.be.domain.controller.response.home.HomeResponse;
import com.team7.be.domain.entity.GroupSchedule;
import com.team7.be.domain.entity.UserGroup;
import com.team7.be.domain.entity.availableSchedule.AvailableSchedule;
import com.team7.be.domain.repository.AvailableScheduleRepository;
import com.team7.be.domain.repository.GroupScheduleRepository;
import com.team7.be.domain.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class HomeService {
    private final AvailableScheduleRepository availableScheduleRepository;
    private final GroupScheduleRepository groupScheduleRepository;
    private final UserGroupRepository userGroupRepository;

    public HomeResponse getHomeInfo(Long userId) {
        Optional<AvailableSchedule> availableScheduleOptional =availableScheduleRepository.findFirstByUserId(userId);
        if(availableScheduleOptional.isEmpty()) throw new RuntimeException();

        Long groupScheduleId = availableScheduleOptional.get().getGroupScheduleId();

        Optional<GroupSchedule> groupScheduleOptional = groupScheduleRepository.findByGroupScheduleId(groupScheduleId);
        if(groupScheduleOptional.isEmpty()){
            throw new RuntimeException();
        }
        GroupSchedule groupSchedule = groupScheduleOptional.get();

        return HomeResponse.builder()
                .groupName(userGroupRepository.findByUserGroupId(groupSchedule.getUserGroupId()).get().getUserGroupName())
                .scheduleDate(groupSchedule.getScheduleDate())
                .scheduleName(groupSchedule.getScheduleName())
                .build();

    }



}