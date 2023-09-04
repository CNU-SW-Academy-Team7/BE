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

public class CheckedScheduleDto {
    private Long userId;
//    private Long scheduleId;
    private Long groupId;
    private LocalDateTime selectedStartTime;
    private LocalDateTime selectedEndTime;
}
