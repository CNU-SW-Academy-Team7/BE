package com.team7.be.domain.service;

import com.team7.be.domain.controller.response.AvailableScheduleResponse;
import com.team7.be.domain.entity.availableSchedule.AvailableSchedule;
import com.team7.be.domain.repository.AvailableScheduleRepository;
import com.team7.be.domain.service.dto.AvailableScheduleDto;
import com.team7.be.domain.service.dto.AvailableScheduleListDto;
import com.team7.be.global.exception.SaveScheduleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class AvailableScheduleService {
    private final AvailableScheduleRepository availableScheduleRepository;


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



}
