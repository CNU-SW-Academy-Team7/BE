package com.team7.be.domain.entity.schedule;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor // 매개변수 없는 생성자
@AllArgsConstructor // 매개변수 있는 생성자
@Builder
public class CheckedSchedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="checkedScheduleId")
    private Long checkedScheduleId;

    private Long userId;
    private Long groupId;
    private LocalDateTime selectedStartTime;
    private LocalDateTime selectedEndTime;
}
