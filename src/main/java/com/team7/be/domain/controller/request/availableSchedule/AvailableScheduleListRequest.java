package com.team7.be.domain.controller.request.availableSchedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team7.be.domain.service.dto.AvailableScheduleDto;
import com.team7.be.domain.service.dto.AvailableScheduleListDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableScheduleListRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss", timezone = "Asia/Seoul")
    private List<LocalDateTime> availableScheduleList;


    public AvailableScheduleListDto toDto(Long groupId, Long scheduleId, Long userId){
        List<AvailableScheduleDto> availableScheduleDtoList = new ArrayList<>();

        availableScheduleList.forEach(
                (schedule ->{
                    availableScheduleDtoList.add(AvailableScheduleDto.builder().availableTime(schedule).build());
                })
        );

        return AvailableScheduleListDto.builder()
                .availableScheduleDtoList(availableScheduleDtoList)
                .userId(userId)
                .groupScheduleId(scheduleId)
                .userGroupId(groupId)
                .build();
    }
}
