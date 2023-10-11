package com.team7.be.domain.controller;

import com.team7.be.domain.controller.request.groupSchedule.CreateGroupScheduleRequest;
import com.team7.be.domain.controller.response.groupSchedule.CreateGroupScheduleResponse;
import com.team7.be.domain.service.GroupScheduleService;
import com.team7.be.domain.service.dto.GroupScheduleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroupScheduleController {

    private final GroupScheduleService groupScheduleService;

    @PostMapping("/createGroupSchedule/{groupId}")
    public ResponseEntity<CreateGroupScheduleResponse> createGroupSchedule(@PathVariable Long groupId, @RequestBody CreateGroupScheduleRequest createGroupScheduleRequest) {
        GroupScheduleDto groupScheduleDto = createGroupScheduleRequest.toDto(groupId);
        Long scheduleId = groupScheduleService.createGroupSchedule(groupId, groupScheduleDto);
        CreateGroupScheduleResponse response = new CreateGroupScheduleResponse(scheduleId, groupId);
        return ResponseEntity.ok(response);
    }
}
