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
public class HomeResponse {
    private String groupName;
    private LocalDateTime date;
    private String scheduleName;
}
