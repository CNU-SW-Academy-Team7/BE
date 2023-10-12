package com.team7.be.domain.controller.response.home;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeResponse {
    private String groupName;
    private LocalDate scheduleDate;
    private String scheduleName;
}
