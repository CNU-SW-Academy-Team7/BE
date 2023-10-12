package com.team7.be.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor // 매개변수 없는 생성자
@AllArgsConstructor // 매개변수 이쓴 생성자
@Builder
public class UserGroup {
    //그룹 구분을 위한 id, 자동 생성 사용
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userGroupId")
    private Long userGroupId;

    //그룹 이름,UNIQUE제약조건 설정
    @Column(name="userGroupName",unique = true)
    private String userGroupName;

    @JoinColumn(name="userId")
    private Long userId;

}