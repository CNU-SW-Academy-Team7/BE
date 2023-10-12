package com.team7.be.domain.service;

import com.team7.be.domain.controller.response.home.HomeResponse;
import com.team7.be.domain.entity.GroupSchedule;
import com.team7.be.domain.entity.availableSchedule.AvailableSchedule;
import com.team7.be.domain.repository.AvailableScheduleRepository;
import com.team7.be.domain.repository.GroupScheduleRepository;
import com.team7.be.domain.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class HomeService {
    private final AvailableScheduleRepository availableScheduleRepository;
    private final GroupScheduleRepository groupScheduleRepository;
    private final UserGroupRepository userGroupRepository;

    public HomeResponse getHomeInfo(Long userId) {
        AvailableSchedule availableSchedule =availableScheduleRepository.findAvailableScheduleByUserId(userId);
        Long availableUserGroupId = availableSchedule.getUserGroupId();
        Optional<GroupSchedule> groupScheduleOptional = groupScheduleRepository.findByUserGroupId(availableUserGroupId);
        if(groupScheduleOptional.isEmpty()){
            throw new RuntimeException();
        }
        GroupSchedule groupSchedule = groupScheduleOptional.get();

        return HomeResponse.builder()
                .groupName(userGroupRepository.findByUserGroupId(availableUserGroupId).getGroupName())
                .scheduleDate(groupSchedule.getScheduleDate())
                .scheduleName(groupSchedule.getScheduleName())
                .build();

    }


}