package com.team7.be.domain.controller;


import com.team7.be.domain.controller.request.group.CreateGroupRequest;
import com.team7.be.domain.service.UserGroupService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
}
