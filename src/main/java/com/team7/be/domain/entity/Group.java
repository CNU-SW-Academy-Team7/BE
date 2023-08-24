package com.team7.be.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor // 매개변수 없는 생성자
@AllArgsConstructor // 매개변수 이쓴 생성자
@Builder
public class Group {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="groupId")
    private Long id;

    @Column(name="groupName",unique = true)
    private String groupName;
    
    //추후 추가 기능을 위한 추가적인 정보 필요

}