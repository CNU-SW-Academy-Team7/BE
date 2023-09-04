package com.team7.be.domain.controller;

import com.team7.be.domain.controller.request.schedule.CheckedScheduleListRequest;
import com.team7.be.domain.controller.request.schedule.CheckedScheduleRequest;
import com.team7.be.domain.service.CheckedScheduleService;
import com.team7.be.domain.service.dto.CheckedScheduleListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
}
