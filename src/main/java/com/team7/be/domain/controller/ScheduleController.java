package com.team7.be.domain.controller;

import com.team7.be.domain.controller.request.schedule.CheckedScheduleListRequest;
import com.team7.be.domain.controller.request.schedule.CheckedScheduleRequest;
import com.team7.be.domain.controller.response.CheckedScheduleResponse;
import com.team7.be.domain.service.CheckedScheduleService;
import com.team7.be.domain.service.dto.CheckedScheduleListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
//    private final ScheduleService scheduleService;
    private final CheckedScheduleService checkedScheduleService;
    @PostMapping("/checkedSchedule/{groupId}")
    public ResponseEntity<Void> saveCheckedSchedule(@PathVariable Long groupId, @RequestBody CheckedScheduleListRequest checkedScheduleListRequest){
        checkedScheduleService.saveCheckedSchedule(checkedScheduleListRequest.toDto(groupId));

        URI uri = UriComponentsBuilder.fromPath("/schedule/{groupId}")
                .buildAndExpand(groupId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/checkedSchedule/{groupId}")
    public ResponseEntity<List<CheckedScheduleResponse>> getCheckedSchedule(@PathVariable Long groupId){
        List<CheckedScheduleResponse> availableSchedulList = checkedScheduleService.getCheckedSchedule(groupId);
        return ResponseEntity.ok(availableSchedulList);
    }
}
