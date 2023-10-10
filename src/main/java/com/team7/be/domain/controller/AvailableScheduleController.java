package com.team7.be.domain.controller;

import com.team7.be.domain.controller.request.availableSchedule.AvailableScheduleListRequest;
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


    @PostMapping("/availableSchedule/{groupId}")
    public ResponseEntity<Void> saveCheckedSchedule(@PathVariable Long groupId, @RequestBody AvailableScheduleListRequest availableScheduleListRequest){
        availableScheduleService.saveAvailableSchedule(availableScheduleListRequest.toDto(groupId));

        URI uri = UriComponentsBuilder.fromPath("/schedule/{userGroupId}")
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
