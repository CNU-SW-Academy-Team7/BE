package com.team7.be.domain.service;

import com.team7.be.domain.controller.response.availableSchedule.AvailableScheduleListResponse;
import com.team7.be.domain.controller.response.availableSchedule.AvailableScheduleResponse;
import com.team7.be.domain.controller.response.availableSchedule.AvailableScheduleResultResponse;
import com.team7.be.domain.entity.availableSchedule.AvailableSchedule;
import com.team7.be.domain.repository.AvailableScheduleRepository;

import com.team7.be.domain.service.dto.AvailableScheduleListDto;
import com.team7.be.global.exception.SaveScheduleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class AvailableScheduleService {
    private final AvailableScheduleRepository availableScheduleRepository;


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
                                .groupScheduleId(availableScheduleListDto.getGroupScheduleId())
                                .userGroupId(availableScheduleListDto.getUserGroupId())
                                .userId(availableScheduleListDto.getUserId())
                                .availableTime(schedule.getAvailableTime())
                                .build();

                        availableScheduleRepository.save(availableSchedule);
                    })
            );
        }catch (Exception e){
            throw new SaveScheduleException("저장에 실패하였습니다.");
        }
    }


    public AvailableScheduleListResponse getAvailableGroupSchedule(Long scheduleId,Long groupId){
        Map<LocalDateTime,Integer> resultMap = getResultMap(groupId,scheduleId);
        List<AvailableScheduleResponse> availableScheduleResponsesList = new ArrayList<>();

        for (Map.Entry<LocalDateTime,Integer> entry : resultMap.entrySet()){
            availableScheduleResponsesList.add(
                    AvailableScheduleResponse.builder()
                            .availableTime(entry.getKey())
                            .availableNum(entry.getValue())
                            .build()
            );
        }

        return AvailableScheduleListResponse.builder()
                .availableScheduleResponseList(availableScheduleResponsesList).build();
    }



    public AvailableScheduleResultResponse getResult(Long scheduleId,Long groupId){
        Map<LocalDateTime,Integer> resultMap = getResultMap(groupId,scheduleId);
        int max=getMaxValue(resultMap);

        LocalDateTime startAvailableTime=LocalDateTime.MIN;
        LocalDateTime endAvailableTime=LocalDateTime.MIN;

        for (Map.Entry<LocalDateTime,Integer> entry : resultMap.entrySet()){
           if(entry.getValue()==max){
               if(entry.getKey().isAfter(startAvailableTime)) startAvailableTime = entry.getKey();
               endAvailableTime = entry.getKey();
           } else if (entry.getValue()<max && entry.getKey().minus(30, ChronoUnit.MINUTES).isEqual(endAvailableTime)){
                return AvailableScheduleResultResponse.builder()
                        .availableStartTime(startAvailableTime)
                        .availableEndTime(endAvailableTime)
                        .availableNum(max)
                        .build();

           }
        }
        return null;
    }

    public Map<LocalDateTime,Integer> getResultMap(Long groupId, Long scheduleId){
        List<AvailableSchedule> getGroupScheduleList = availableScheduleRepository.findByAvailableScheduleIdAndUserGroupId(scheduleId,groupId);
        Map<LocalDateTime,Integer> resultMap = new HashMap<>();

        getGroupScheduleList.forEach(

                availableSchedule -> {
                    LocalDateTime currentDateValue = availableSchedule.getAvailableTime();
                    if(!resultMap.containsKey(currentDateValue)) resultMap.put(availableSchedule.getAvailableTime(),1);
                    else{
                        int count = resultMap.get(currentDateValue)+1;
                        resultMap.put(currentDateValue,count);
                    }

                });

        return resultMap;
    }

    public int getMaxValue(Map<LocalDateTime,Integer> inputMap){
        int max=Integer.MIN_VALUE;

        for (Map.Entry<LocalDateTime,Integer> entry : inputMap.entrySet()){
            if(entry.getValue()>max){
                max=entry.getValue();
            }
        }
        return max;
    }




}