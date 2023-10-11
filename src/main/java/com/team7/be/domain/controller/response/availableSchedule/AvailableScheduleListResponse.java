package com.team7.be.domain.controller.response.availableSchedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableScheduleListResponse {
    private List<AvailableScheduleResponse> availableScheduleResponseList;

}
