package com.team7.be.domain.controller;

import com.team7.be.domain.controller.request.availableSchedule.AvailableScheduleListRequest;
import com.team7.be.domain.controller.response.AvailableScheduleListResponse;
import com.team7.be.domain.controller.request.group.CreateGroupScheduleRequest;
import com.team7.be.domain.controller.response.AvailableScheduleResponse;
import com.team7.be.domain.controller.response.CreateGroupScheduleResponse;
import com.team7.be.domain.service.AvailableScheduleService;
import com.team7.be.domain.service.dto.CreateGroupScheduleDto;
import com.team7.be.global.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AvailableScheduleController {

    private final AvailableScheduleService availableScheduleService;

    @PostMapping("/availableSchedule/{groupId}/{scheduleId}/{userId}")
    public ResponseEntity<Void> saveCheckedSchedule(@PathVariable Long groupId,@PathVariable Long scheduleId, @PathVariable Long userId, @RequestBody AvailableScheduleListRequest availableScheduleListRequest){
        availableScheduleService.saveAvailableSchedule(availableScheduleListRequest.toDto(groupId, scheduleId, userId));

        URI uri = UriComponentsBuilder.fromPath("/schedule/{groupId}")
                .buildAndExpand(groupId)
                .toUri();

        System.out.println();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/availableSchedule/{groupId}")
    public ResponseEntity<AvailableScheduleListResponse> getCheckedSchedule(@PathVariable Long availableSchedule, @PathVariable Long groupId){
        AvailableScheduleListResponse availableSchedulList = availableScheduleService.getAvailableGroupSchedule(availableSchedule, groupId);
        return ResponseEntity.ok(availableSchedulList);
    }

    @GetMapping("/availableScheduleList/{groupId}/{scheduleId}")
    public ResponseEntity<List<AvailableScheduleResponse>> getAvailableScheduleList(@PathVariable Long groupId, @PathVariable Long scheduleId) {
        List<AvailableScheduleResponse> availableScheduleList = availableScheduleService.getAvailableScheduleList(groupId, scheduleId);
        return ResponseEntity.ok(availableScheduleList);
    }

    @GetMapping("/availableScheduleResult/{groupId}/{scheduleId}")
    public ResponseEntity<AvailableScheduleResponse> getAvailableScheduleResult(@PathVariable Long groupId, @PathVariable Long scheduleId) {
        AvailableScheduleResponse availableResult = availableScheduleService.getAvailableScheduleResult(groupId, scheduleId);
        return ResponseEntity.ok(availableResult);
    }

    @PostMapping("/createGroupSchedule/{groupId}")
    public ResponseEntity<CreateGroupScheduleResponse> createGroupSchedule(@PathVariable Long groupId, @RequestBody CreateGroupScheduleRequest createGroupScheduleRequest) {
        CreateGroupScheduleDto createGroupScheduleDto = createGroupScheduleRequest.toDto(groupId);
        Long scheduleId = availableScheduleService.createGroupSchedule(groupId, createGroupScheduleDto);
        CreateGroupScheduleResponse response = new CreateGroupScheduleResponse(scheduleId, groupId);
        return ResponseEntity.ok(response);
    }
}
