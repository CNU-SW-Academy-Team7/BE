package com.team7.be.domain.controller.response.userGroup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetGroupResponse {
    private String groupName;
    private List<String> memberList;
}
