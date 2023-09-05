package com.team7.be.domain.service;

import com.team7.be.domain.controller.response.CheckedScheduleResponse;
import com.team7.be.domain.entity.schedule.CheckedSchedule;
import com.team7.be.domain.repository.CheckedScheduleRepository;
import com.team7.be.domain.service.dto.CheckedScheduleDto;
import com.team7.be.domain.service.dto.CheckedScheduleListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class CheckedScheduleService {
    private final CheckedScheduleRepository checkedScheduleRepository;

    @Transactional
    public Long save(CheckedScheduleDto checkedScheduleDTO){
        CheckedSchedule checkedSchedule = CheckedSchedule.builder()
                .groupId(checkedScheduleDTO.getGroupId())
                .selectedStartTime(checkedScheduleDTO.getSelectedStartTime())
                .selectedEndTime(checkedScheduleDTO.getSelectedEndTime())
                .build();

        return checkedScheduleRepository.save(checkedSchedule).getGroupId();
    }

    @Transactional
    public void saveCheckedSchedule(CheckedScheduleListDto checkedScheduleListDto){
        checkedScheduleListDto.getCheckedScheduleList().forEach(
                (schedule ->{
                    CheckedSchedule checkedSchedule = CheckedSchedule.builder()
                            .groupId(schedule.getGroupId())
                            .selectedStartTime(schedule.getSelectedStartTime())
                            .selectedEndTime(schedule.getSelectedEndTime())
                            .build();

                    checkedScheduleRepository.save(checkedSchedule);
                })
        );
    }


    public List<CheckedScheduleResponse> getCheckedSchedule(Long groupId){
        List<CheckedSchedule> getGroupScheduleList = checkedScheduleRepository.findByGroupId(groupId);
        List<CheckedScheduleResponse> checkedScheduleResponsesList = new ArrayList<>();
        getGroupScheduleList.forEach(
                (checkedSchedule -> {
                    checkedScheduleResponsesList.add(
                            CheckedScheduleResponse.builder()
                                    .checkedStartTime(checkedSchedule.getSelectedStartTime())
                                    .checkedEndTime(checkedSchedule.getSelectedEndTime())
                                    .build()
                    );
                })
        );

        return checkedScheduleResponsesList;

    }



}
