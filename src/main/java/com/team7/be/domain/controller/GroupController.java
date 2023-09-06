package com.team7.be.domain.controller;

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

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest){
        String  userId = memberService.signUp(signUpRequest);
        URI uri = fromPath("/member/{userId}")
                .buildAndExpand(userId)
                .toUri();

        return ResponseEntity.created(uri).build();
        }
    }

