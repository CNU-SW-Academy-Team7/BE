package com.team7.be.domain.controller.request.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInRequest {
    private String userId;
    private String password;
}
