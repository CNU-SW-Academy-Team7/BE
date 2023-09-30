package com.team7.be.domain.service;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class AvailableScheduleService {
    private final AvailableScheduleRepository availableScheduleRepository;
    UserGroupRepository userGroupRepository;
    ScheduleRepository scheduleRepository;


    @Transactional
    public void saveAvailableSchedule(AvailableScheduleListDto availableScheduleListDto){
        try {


            availableScheduleListDto.getAvailableScheduleDtoList().forEach(
                    (schedule -> {
                        AvailableSchedule availableSchedule = AvailableSchedule.builder()
//                            .userName(schedule.getUserName())
                                .groupId(schedule.getGroupId())
                                .availableStartTime(schedule.getAvailableStartTime())
                                .availableEndTime(schedule.getAvailableEndTime())
                                .build();

                        availableScheduleRepository.save(availableSchedule);
                    })
            );
        }catch (Exception e){
            throw new SaveScheduleException("저장에 실패하였습니다.");
        }
    }


    public List<AvailableScheduleResponse> getAvailableSchedule(Long groupId){
        List<AvailableSchedule> getGroupScheduleList = availableScheduleRepository.findByGroupId(groupId);
        List<AvailableScheduleResponse> availableScheduleResponsesList = new ArrayList<>();
        getGroupScheduleList.forEach(
                (availableSchedule -> {
                    availableScheduleResponsesList.add(
                            AvailableScheduleResponse.builder()
                                    .availableStartTime(availableSchedule.getAvailableStartTime())
                                    .availableEndTime(availableSchedule.getAvailableEndTime())
                                    .build()
                    );
                })
        );

        return availableScheduleResponsesList;

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
