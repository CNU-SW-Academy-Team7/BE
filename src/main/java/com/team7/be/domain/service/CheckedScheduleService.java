package com.team7.be.domain.service;

import com.team7.be.domain.controller.response.CheckedScheduleResponse;
import com.team7.be.domain.controller.response.OneWeekScheduleResponse;
import com.team7.be.domain.entity.schedule.CheckedSchedule;
import com.team7.be.domain.repository.CheckedScheduleRepository;
import com.team7.be.domain.service.dto.CheckedScheduleDto;
import com.team7.be.domain.service.dto.CheckedScheduleListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    List<CheckedScheduleResponse> getCheckedSchedule(Long scheduleId, Long groupId){
        List<CheckedScheduleResponse> checkedScheduleResponsesList = new ArrayList<>();
        checkedScheduleResponsesList.forEach(
                (schedule -> {
                    checkedScheduleResponsesList.add(
                            CheckedScheduleResponse.builder()
                                    .scheduleId(scheduleId)
                                    .checkedStartTime(schedule.getCheckedStartTime())
                                    .checkedEndTime(schedule.getCheckedEndTime())
                                    .build()
                    );
                })
        );

        return checkedScheduleResponsesList;

    }
    public List<OneWeekScheduleResponse> getAllOneWeekSchedule(LocalDate selectedStartTime){
        List<CheckedSchedule>ScheduleList = checkedScheduleRepository.getAllOneWeekSchedule(selectedStartTime,selectedStartTime.plusWeeks(1));
        List<OneWeekScheduleResponse> ScheduleResponsesList=new ArrayList<>();
        ScheduleList.forEach(s->{
            ScheduleResponsesList.add(
                    OneWeekScheduleResponse.builder()
                            .scheduleId(s.getCheckedScheduleId())
                            .checkedStartTime(s.getSelectedStartTime())
                            .checkedEndTime(s.getSelectedEndTime())
                            .build()
            );
        });

        return ScheduleResponsesList;

    }
    public List<OneWeekScheduleResponse> getGroupOneWeekSchedule(Long groupId, LocalDate selectedStartTime){
        List<CheckedSchedule>ScheduleList = checkedScheduleRepository.getGroupOneWeekSchedule(groupId,selectedStartTime,selectedStartTime.plusWeeks(1));
        List<OneWeekScheduleResponse> ScheduleResponsesList=new ArrayList<>();
        ScheduleList.forEach(s->{
            ScheduleResponsesList.add(
                    OneWeekScheduleResponse.builder()
                            .scheduleId(s.getCheckedScheduleId())
                            .checkedStartTime(s.getSelectedStartTime())
                            .checkedEndTime(s.getSelectedEndTime())
                            .build()
            );
        });

        return ScheduleResponsesList;

    }


}
