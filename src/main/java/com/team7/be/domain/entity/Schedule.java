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

    @ManyToOne
    @JoinColumn(name="groupId")
    private Group groupId; // Group 테이블의 FK

    @ManyToOne
    @JoinColumn(name="memberId")
    private Member memberId; // Member 테이블의 FK

    private LocalDateTime selectedStartDate;
    private LocalDateTime selectedEndDate;
}
