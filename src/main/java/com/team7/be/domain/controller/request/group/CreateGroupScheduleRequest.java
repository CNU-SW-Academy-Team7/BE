package com.team7.be.domain.controller.request.group;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.team7.be.domain.service.dto.AvailableScheduleDto;
import com.team7.be.domain.service.dto.CreateGroupScheduleDto;
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
    private LocalDate date;

    private String scheduleName;

    public CreateGroupScheduleDto toDto(Long groupId){
        return CreateGroupScheduleDto.builder()
                .date(this.date)
                .scheduleName(this.scheduleName)
                .build();
    }
}
