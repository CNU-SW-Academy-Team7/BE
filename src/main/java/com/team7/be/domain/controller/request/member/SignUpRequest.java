package com.team7.be.domain.controller.request.member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequest {
    private String userName;
    private String userEmail;
    private String userPw;

}

// 사용자로부터 요청과 함게 입력되어야 하는 회원가입 요구사장
