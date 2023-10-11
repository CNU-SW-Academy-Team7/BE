package com.team7.be.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="groupScheduleId")
    private Long groupScheduleId; // PK

    @ManyToOne
    @JoinColumn(name="userGroupId")
    private UserGroup userGroupId; // UserGroup 테이블의 FK

    @Column(name = "scheduleDate")
    private LocalDate scheduleDate;


    private String scheduleName;
}
