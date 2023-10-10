package com.team7.be.domain.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGroupScheduleResponse {
    private Long scheduleId;
    private Long groupId;
}
