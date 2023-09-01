package com.team7.be.domain.controller;

import com.team7.be.domain.controller.request.member.SignInRequest;
import com.team7.be.domain.controller.request.member.SignUpRequest;
import com.team7.be.domain.entity.Member;
import com.team7.be.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;



@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest){
        String  userId = memberService.signUp(signUpRequest);
        //service의 signUp 메소드를 사용하여 저장된 의 고유 id를 저장
        URI uri = fromPath("/member/{userId}")
                .buildAndExpand(userId)
                .toUri();
        //회원가입 진행 시 사용자별로 식별할 수 있는 그들읠 userId를 uri에 지정하여 개별적인 endpoint로 이동할 수 있도록 한다.
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody SignInRequest signInRequest) {
        Optional<Member> findMember = memberService.signIn(signInRequest);
        if (findMember.isPresent()) {
            return ResponseEntity.ok("로그인 성공");
        } else return ResponseEntity.badRequest().body("로그인 실패");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("회원 탈퇴 성공");
    }
}
