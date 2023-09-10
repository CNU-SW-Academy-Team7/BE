package com.team7.be.domain.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AvailableScheduleDto {
//    private String userName;
//    private Long scheduleId;
    private Long groupId;
    private LocalDateTime availableStartTime;
    private LocalDateTime availableEndTime;
}
