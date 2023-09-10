package com.team7.be.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableScheduleListDto {
    private List<AvailableScheduleDto> availableScheduleDtoList;

    private String userName;

}
