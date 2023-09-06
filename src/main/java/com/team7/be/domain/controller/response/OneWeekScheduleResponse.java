package com.team7.be.domain.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OneWeekScheduleResponse {
    private Long scheduleId;
    private LocalDateTime checkedStartTime;
    private LocalDateTime checkedEndTime;
}
