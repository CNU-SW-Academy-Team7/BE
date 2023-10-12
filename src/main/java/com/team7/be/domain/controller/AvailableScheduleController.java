package com.team7.be.domain.controller;

import com.team7.be.domain.controller.request.availableSchedule.AvailableScheduleListRequest;
import com.team7.be.domain.controller.response.availableSchedule.AvailableScheduleListResponse;
import com.team7.be.domain.controller.response.AvailableScheduleResponse;
import com.team7.be.domain.service.AvailableScheduleService;
import lombok.RequiredArgsConstructor;
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

    public ResponseEntity<List<AvailableScheduleResponse>> getCheckedSchedule(@PathVariable Long groupId){
        List<AvailableScheduleResponse> availableSchedulList = availableScheduleService.getAvailableSchedule(groupId);
        return ResponseEntity.ok(availableSchedulList);
    }
}
