package com.team7.be.domain.entity.availableSchedule;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @JoinColumn(name = "groupScheduleId")
    private Long groupScheduleId;

    @JoinColumn(name = "userId")
    private Long userId;

//  @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "userGroupId")
    private Long userGroupId;


    @Column(name = "availableTime")
    private LocalDateTime availableTime;
}
