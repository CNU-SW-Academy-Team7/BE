package com.team7.be.domain.service;


import com.team7.be.domain.controller.request.member.SignUpRequest;
import com.team7.be.domain.entity.Member;
import com.team7.be.domain.repository.MemberRepository;
import com.team7.be.global.exception.EmailNotFoundException;
import com.team7.be.global.exception.PasswordMismatchException;
import com.team7.be.global.exception.UserEmailDuplicatedException;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class MemberService {
    private final MemberRepository memberRepository;
//    private BCryptPasswordEncoder passwordEncoder;


    @Transactional
    public String signUp(SignUpRequest signUpRequest){
        checkDuplicateUserEmail(signUpRequest.getUserEmail());
        // 사용자의 id를 가져와서 이미 repository에 존재하는 userid인지 체크
        Member member = Member.builder()
                .userEmail(signUpRequest.getUserEmail())
                .userPw(signUpRequest.getUserPw())
                .userName(signUpRequest.getUserName())
                .build();
        // 사용자로부터 전달된 member정보(signUpRequest)에서 필요한 정보(memberID)를 가져와 Member엔티티를 build한다.
        return memberRepository.save(member).getUserName(); // 저장한 member의 userid를 반환한다.
    } // Controller에서 해당 메소드 사용

    private void checkDuplicateUserEmail(String userEmail){
        if(memberRepository.findByUserEmail(userEmail).isPresent()){
            throw new UserEmailDuplicatedException("이미 존재하는 사용자 입니다.");
        }
        //member에 대한 정보를 저장하고있는 repo에서 입력받은 userid가 존재하는 지에 대한 여부를 판단.
        // 이미 member 정보가 존재할경우 이를 알림.
    }


//    public Optional<Member> signIn(SignInRequest signInRequest) {
//        Optional<Member> findMember = memberRepository.findByUserId(signInRequest.getUserId());
//        if(findMember.isPresent()) {
//            Member member = findMember.get();
//            if (isPasswordValid(signInRequest.getPassword(), member.getUserPw())) {
//                return findMember;
//            }
//        }
//        return Optional.empty();
//    }

    public SignInResponse signIn(String userEmail, String userPw){
        Member findMember = memberRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new EmailNotFoundException("사용자를 찾을 수 없습니다.")
        );
        if(!findMember.matchPassword(userPw)){
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }
        return SignInResponse.builder()
                .userName(findMember.getUserName())
                .build();
    }

//    private boolean isPasswordValid(String inputPassword, String userPassword) {
////        return passwordEncoder.matches(inputPassword, userPassword);
//        return userPassword.equals(inputPassword);
//    }

    public void deleteMember(Long id) {
        Optional<Member> findMember = memberRepository.findById(id);
        if (findMember.isPresent()) {
            Member member = findMember.get();
            memberRepository.delete(member);

        } else throw new IllegalArgumentException("회원을 찾을 수 없습니다.");
    }
}
