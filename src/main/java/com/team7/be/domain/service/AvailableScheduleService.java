package com.team7.be.domain.service;

import com.team7.be.domain.controller.response.AvailableScheduleListResponse;
import com.team7.be.domain.controller.response.AvailableScheduleResponse;
import com.team7.be.domain.entity.Schedule;
import com.team7.be.domain.entity.UserGroup;
import com.team7.be.domain.entity.availableSchedule.AvailableSchedule;
import com.team7.be.domain.repository.AvailableScheduleRepository;

import com.team7.be.domain.repository.ScheduleRepository;
import com.team7.be.domain.repository.UserGroupRepository;
import com.team7.be.domain.service.dto.AvailableScheduleDto;
import com.team7.be.domain.service.dto.AvailableScheduleListDto;
import com.team7.be.domain.service.dto.CreateGroupScheduleDto;
import com.team7.be.global.exception.GroupNotFoundException;
import com.team7.be.global.exception.SaveScheduleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class AvailableScheduleService {
    private final AvailableScheduleRepository availableScheduleRepository;
    private final UserGroupRepository userGroupRepository;
    private final ScheduleRepository scheduleRepository;


    @Transactional
    public void saveAvailableSchedule(AvailableScheduleListDto availableScheduleListDto){
        List<LocalDateTime> availableScheduleList = new ArrayList<>();

        try {
            availableScheduleListDto.getAvailableScheduleDtoList().forEach(
                    availableScheduleDto -> {
                        availableScheduleList.add(availableScheduleDto.getAvailableTime());
                    }
            );
        }catch (Exception e){
            throw new SaveScheduleException("가능 시간을 불러오는데 실패하였습니다.");
        }

        try {

            availableScheduleListDto.getAvailableScheduleDtoList().forEach(
                    (schedule -> {
                        AvailableSchedule availableSchedule = AvailableSchedule.builder()
                                .availableScheduleId(availableScheduleListDto.getScheduleId())
                                .userId(availableScheduleListDto.getUserId())
                                .groupId(availableScheduleListDto.getGroupId())
                                .build();

                        availableScheduleRepository.save(availableSchedule);
                    })
            );
        }catch (Exception e){
            throw new SaveScheduleException("저장에 실패하였습니다.");
        }
    }


    public AvailableScheduleListResponse getAvailableGroupSchedule(Long groupId,Long scheduleId){
        List<AvailableSchedule> getGroupScheduleList = availableScheduleRepository.findByAvailableScheduleIdAndGroupId(scheduleId,groupId);
        List<AvailableScheduleResponse> availableScheduleResponsesList = new ArrayList<>();
        getGroupScheduleList.forEach(
                (availableSchedule -> {
                    availableScheduleResponsesList.add(
                            AvailableScheduleResponse.builder()
                                    .availableTime(availableSchedule.getAvailableTime())
                                    .build()
                    );
                })
        );


        return AvailableScheduleListResponse.builder()
                .availableScheduleResponseList(availableScheduleResponsesList).build();

    }

    public Long createGroupSchedule(Long groupId, CreateGroupScheduleDto createGroupScheduleDto) {

        Optional<UserGroup> groupOptional = userGroupRepository.findById(groupId);
        if (groupOptional.isEmpty()) {
            throw new GroupNotFoundException("그룹이 존재하지 않습니다.");
        }
        UserGroup group = groupOptional.get();
        Schedule schedule = Schedule.builder()
                .userGroupId(group)
                .date(createGroupScheduleDto.getDate())
                .scheduleName(createGroupScheduleDto.getScheduleName())
                .build();

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return savedSchedule.getScheduleId();
    }

}
