package com.team7.be.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId; // PK

    @JoinColumn(name="groupId")
    private Long groupId; // Group 테이블의 FK

    @JoinColumn(name="memberId")
    private Long memberId; // Member 테이블의 FK

    private LocalDateTime selectedStartDate;
    private LocalDateTime selectedEndDate;
}
