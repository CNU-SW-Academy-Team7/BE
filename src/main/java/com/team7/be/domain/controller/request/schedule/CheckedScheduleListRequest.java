package com.team7.be.domain.controller.request.schedule;

import com.team7.be.domain.service.dto.CheckedScheduleDto;
import com.team7.be.domain.service.dto.CheckedScheduleListDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckedScheduleListRequest {

    private List<CheckedScheduleRequest> checkedScheduleRequestList;

    public CheckedScheduleListDto toDto(Long groupId){
        List<CheckedScheduleDto> checkedScheduleDtoList = new ArrayList<>();

        checkedScheduleRequestList.forEach(
                (schedule ->{
                    checkedScheduleDtoList.add(schedule.toDto(groupId)
                    );
                })
        );

        return CheckedScheduleListDto.builder()
                .checkedScheduleList(checkedScheduleDtoList)
                .build();
    }
}
