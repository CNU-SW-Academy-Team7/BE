package com.team7.be.domain.controller.request.schedule;

import com.team7.be.domain.service.dto.CheckedScheduleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckedScheduleRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime selectedStartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime selectedEndTime;

    public CheckedScheduleDto toDto(Long groupId){
        return CheckedScheduleDto.builder()
                .groupId(groupId)
                .selectedStartTime(this.selectedStartTime)
                .selectedEndTime(this.selectedEndTime)
                .build();
    }

}
