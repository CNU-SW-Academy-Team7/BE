package com.team7.be.domain.controller;

import com.team7.be.domain.controller.request.availableSchedule.AvailableScheduleListRequest;
import com.team7.be.domain.controller.request.groupSchedule.CreateGroupScheduleRequest;
import com.team7.be.domain.controller.response.availableSchedule.AvailableScheduleListResponse;
import com.team7.be.domain.controller.response.availableSchedule.AvailableScheduleResultResponse;
import com.team7.be.domain.controller.response.groupSchedule.CreateGroupScheduleResponse;
import com.team7.be.domain.service.AvailableScheduleService;
import com.team7.be.domain.service.GroupScheduleService;
import com.team7.be.domain.service.UserGroupService;
import com.team7.be.domain.service.dto.GroupScheduleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AvailableScheduleController {

    private final AvailableScheduleService availableScheduleService;
    private final UserGroupService userGroupService;
    private final GroupScheduleService groupScheduleService;

    @PostMapping("/availableSchedule/{groupId}/{availableScheduleId}/{userId}")
    public ResponseEntity<Void> saveAvailableSchedule(@PathVariable Long groupId,@PathVariable Long availableScheduleId, @PathVariable Long userId, @RequestBody AvailableScheduleListRequest availableScheduleListRequest){
        availableScheduleService.saveAvailableSchedule(availableScheduleListRequest.toDto(groupId, availableScheduleId, userId));
        userGroupService.enrollGroup(userId,groupId);
        groupScheduleService.enrollGroupSchedule(groupId,availableScheduleId,userId);

        URI uri = UriComponentsBuilder.fromPath("/schedule/{groupId}")
                .buildAndExpand(groupId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/availableScheduleList/{groupId}/{availableScheduleId}")
    public ResponseEntity<AvailableScheduleListResponse> getAvailableSchedule(@PathVariable Long availableScheduleId, @PathVariable Long groupId){
        AvailableScheduleListResponse availableScheduleList = availableScheduleService.getAvailableGroupSchedule(availableScheduleId, groupId);
        return ResponseEntity.ok(availableScheduleList);
    }

    @GetMapping("/availableScheduleResult/{groupId}/{availableScheduleId}")
    public ResponseEntity<AvailableScheduleResultResponse> getAvailableScheduleResult(@PathVariable Long availableScheduleId, @PathVariable Long groupId){
        AvailableScheduleResultResponse availableScheduleResultResponse = availableScheduleService.getResult(availableScheduleId, groupId);
        return ResponseEntity.ok(availableScheduleResultResponse);
    }



}