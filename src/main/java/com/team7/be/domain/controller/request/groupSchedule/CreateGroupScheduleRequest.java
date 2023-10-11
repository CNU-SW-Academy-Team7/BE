package com.team7.be.domain.controller.request.groupSchedule;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.team7.be.domain.service.dto.GroupScheduleDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGroupScheduleRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate scheduleDate;

    private String scheduleName;

    public GroupScheduleDto toDto(Long groupId){
        return GroupScheduleDto.builder()
                .scheduleDate(this.scheduleDate)
                .scheduleName(this.scheduleName)
                .build();
    }
}
