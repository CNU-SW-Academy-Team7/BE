package com.team7.be.domain.service;

import com.team7.be.domain.entity.GroupSchedule;
import com.team7.be.domain.entity.UserGroup;
import com.team7.be.domain.repository.GroupScheduleRepository;
import com.team7.be.domain.repository.UserGroupRepository;
import com.team7.be.domain.service.dto.GroupScheduleDto;
import com.team7.be.global.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class GroupScheduleService {
    private final UserGroupRepository userGroupRepository;
    private final GroupScheduleRepository groupScheduleRepository;

    public Long createGroupSchedule(Long groupId, GroupScheduleDto groupScheduleDto) {

        Optional<UserGroup> groupOptional = userGroupRepository.findById(groupId);
        if (groupOptional.isEmpty()) {
            throw new GroupNotFoundException("그룹이 존재하지 않습니다.");
        }

        UserGroup group = groupOptional.get();
        GroupSchedule groupSchedule = GroupSchedule.builder()
                .userGroupId(group)
                .scheduleDate(groupScheduleDto.getScheduleDate())
                .scheduleName(groupScheduleDto.getScheduleName())
                .build();

        GroupSchedule savedGroupSchedule = groupScheduleRepository.save(groupSchedule);
        // try catch 오류 처리 필요

        return savedGroupSchedule.getGroupScheduleId();
    }
}
