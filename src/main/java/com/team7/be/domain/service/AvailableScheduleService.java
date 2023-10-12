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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
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
            availableScheduleList.forEach(
                    (schedule -> {
                        AvailableSchedule availableSchedule = AvailableSchedule.builder()
                                .groupScheduleId(availableScheduleListDto.getGroupScheduleId())
                                .userGroupId(availableScheduleListDto.getUserGroupId())
                                .userId(availableScheduleListDto.getUserId())
                                .availableTime(schedule)
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

        int temp_index=0;
        int maxSum =Integer.MIN_VALUE;
        int currentSum = Integer.MIN_VALUE;
        LocalDateTime maxStart=LocalDateTime.MIN;
        LocalDateTime maxEnd= LocalDateTime.MIN;
        LocalDateTime currentStart=LocalDateTime.MIN;
        LocalDateTime currentEnd=LocalDateTime.MIN;

        for (Map.Entry<LocalDateTime,Integer> entry : resultMap.entrySet()){

            if(temp_index==0){
                maxStart = maxEnd = currentStart = currentEnd = entry.getKey();
                maxSum=currentSum=entry.getValue();
                temp_index++;
            }

            if(entry.getValue().compareTo(resultMap.get(currentEnd))==0 ) {
                currentEnd = entry.getKey();
            }else{
                currentSum = entry.getValue();
                currentStart = entry.getKey();
                currentEnd = entry.getKey();
            }

            if(currentSum>=maxSum){
                maxSum = currentSum;
                maxStart = currentStart;
                maxEnd = currentEnd;
            }
        }

        return AvailableScheduleResultResponse.builder()
                        .availableStartTime(maxStart)
                        .availableEndTime(maxEnd)
                        .availableNum(maxSum)
                        .build();
    }

    public Map<LocalDateTime,Integer> getResultMap(Long groupId, Long scheduleId){
        List<AvailableSchedule> getGroupScheduleList = availableScheduleRepository.findByGroupScheduleIdAndUserGroupId(scheduleId,groupId);
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

        Map<LocalDateTime,Integer> sortedResultMap = new TreeMap<>(resultMap);
        return sortedResultMap;
    }






}