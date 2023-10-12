package com.team7.be.domain.service;

import com.team7.be.domain.entity.GroupSchedule;
import com.team7.be.domain.entity.UserGroup;
import com.team7.be.domain.entity.availableSchedule.AvailableSchedule;
import com.team7.be.domain.repository.AvailableScheduleRepository;
import com.team7.be.domain.repository.GroupScheduleRepository;
import com.team7.be.domain.repository.UserGroupRepository;
import com.team7.be.domain.service.dto.GroupScheduleDto;
import com.team7.be.global.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class GroupScheduleService {
    private final GroupScheduleRepository groupScheduleRepository;
    private final AvailableScheduleRepository availableScheduleRepository;

    public Long createGroupSchedule(Long groupId, GroupScheduleDto groupScheduleDto) {

        GroupSchedule groupSchedule = GroupSchedule.builder()
                .userGroupId(groupId)
                .scheduleDate(groupScheduleDto.getScheduleDate())
                .scheduleName(groupScheduleDto.getScheduleName())
                .build();

        GroupSchedule savedGroupSchedule = groupScheduleRepository.save(groupSchedule);
        // TODO : try catch 오류 처리 필요

        return savedGroupSchedule.getGroupScheduleId();
    }

    public Long enrollGroupSchedule(Long groupId, Long availableScheduleId, Long userId){
        // 1. available -> groupScheduleId
        //2 groupscheduleId -> Date,name 찾기

        Optional<AvailableSchedule> availableScheduleOptional = availableScheduleRepository.findFirstByAvailableScheduleIdAndUserGroupId(availableScheduleId,groupId);
        if(availableScheduleOptional.isEmpty()) throw new RuntimeException(); //TODO : Exception Handling


        Optional<GroupSchedule> groupScheduleOptional = groupScheduleRepository.findByGroupScheduleId(availableScheduleOptional.get().getGroupScheduleId());
        if(groupScheduleOptional.isEmpty()) throw new RuntimeException(); //TODO : exception Handling


        GroupSchedule groupSchedule = GroupSchedule.builder()
                .userGroupId(groupId)
                .scheduleDate(groupScheduleOptional.get().getScheduleDate())
                .scheduleName(groupScheduleOptional.get().getScheduleName())
                .userId(userId)
                .build();

        GroupSchedule savedGroupSchedule = groupScheduleRepository.save(groupSchedule);

        return savedGroupSchedule.getGroupScheduleId();
    }
}
