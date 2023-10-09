package com.team7.be.domain.entity.availableSchedule;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor // 매개변수 없는 생성자
@AllArgsConstructor // 매개변수 있는 생성자
@Builder
public class AvailableSchedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="availableScheduleId")
    private Long availableScheduleId;

    private String userName;

//  @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Long groupId;

    @JoinColumn(name = "scheduleId")
    private Long scheduleId;

    private LocalDateTime availableStartTime;
    private LocalDateTime availableEndTime;
}
