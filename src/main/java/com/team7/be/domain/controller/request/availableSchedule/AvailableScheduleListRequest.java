package com.team7.be.domain.controller.request.availableSchedule;

import com.team7.be.domain.service.dto.AvailableScheduleDto;
import com.team7.be.domain.service.dto.AvailableScheduleListDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableScheduleListRequest {

    private String userName;

    private List<AvailableScheduleRequest> availableScheduleList;


    public AvailableScheduleListDto toDto(Long groupId){
        List<AvailableScheduleDto> availableScheduleDtoList = new ArrayList<>();

        availableScheduleList.forEach(
                (schedule ->{
                    availableScheduleDtoList.add(schedule.toDto(groupId)
                    );
                })
        );

        return AvailableScheduleListDto.builder()
                .availableScheduleDtoList(availableScheduleDtoList)
                .userName(this.userName)
                .build();
    }
}
