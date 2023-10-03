package com.team7.be.domain.controller;


import com.team7.be.domain.controller.request.group.CreateGroupRequest;
import com.team7.be.domain.controller.response.HomeResponse;
import com.team7.be.domain.controller.response.getGroupResponse;
import com.team7.be.domain.service.HomeService;
import com.team7.be.domain.service.UserGroupService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RequiredArgsConstructor
@RestController
public class UserGroupController {
    private final UserGroupService userGroupService;
    @PostMapping("/createGroup")
    public ResponseEntity<Void> createGroup(@RequestBody CreateGroupRequest createGroupRequest){
        Long groupId = userGroupService.saveGroup(createGroupRequest.getGroupName());

        System.out.println(groupId);
        URI uri = fromPath("/group/{userGroupId}")
                .buildAndExpand(groupId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping("/getGroupList/{userId}")
    public ResponseEntity<List<getGroupResponse>> getHome(@PathVariable Long userId) {
        List<getGroupResponse> home = userGroupService.getGroupList(userId);
        return ResponseEntity.ok(home);
    }
}
