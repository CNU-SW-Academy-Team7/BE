package com.team7.be.domain.controller.request.group;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MakeGroupRequest {
    private String GroupName;
}

// 사용자로부터 요청과 함게 입력되어야 하는 그룹 생성 요구사장

