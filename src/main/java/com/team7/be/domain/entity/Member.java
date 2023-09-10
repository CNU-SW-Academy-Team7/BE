package com.team7.be.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor // 매개변수 없는 생성자
@AllArgsConstructor // 매개변수 있는 생성자
@Builder
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 id 값 변
    @Column(name="userId")
    private Long userId;

    private String userEmail;

    private String userName; // 사용자가 회원가입 시 입력한 사용자 이름
    private String userPw; // 사용자가 회원가입 시 입력한 사용자 패스워드


    public boolean matchPassword(String password){
        return this.getUserPw().equals(password);
    }

    // Member 라는 객체의 정보를 저장.
    // 우리는 ERD에 우선적으로 MemberID만 저장했기에 사용자를 식별하는 변수만 선언.
    // 얘네를 저장하는 공간이 repositody/MemberRepository 인터페이스
    // id : 사용자의 고유 id 번호 식별 : 자동 지정. 고유 번호.
    // userId : 사용자가 회원가입 시 입력한 사용자 id


}
