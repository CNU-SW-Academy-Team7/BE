package com.team7.be.domain.controller.request.availableSchedule;

import com.team7.be.domain.service.dto.AvailableScheduleDto;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableScheduleRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime availableTime;


    public AvailableScheduleDto toDto(){
        return AvailableScheduleDto.builder()
                .availableTime(this.availableTime)
                .build();
    }

}
