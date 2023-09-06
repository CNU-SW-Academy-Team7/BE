package com.team7.be.domain.controller;

import com.team7.be.domain.controller.request.member.SignUpRequest;
import com.team7.be.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;


@RequiredArgsConstructor
@RestController
public class GroupController {
    private final MemberService memberService;

}

