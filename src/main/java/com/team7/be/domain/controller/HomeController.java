package com.team7.be.domain.controller;

import com.team7.be.domain.controller.response.home.HomeResponse;
import com.team7.be.domain.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HomeController {
    private final HomeService HomeService;
    @GetMapping("/home/{userId}")
    public ResponseEntity<HomeResponse> getHome(@PathVariable Long userId) {
        try {
            HomeResponse home = HomeService.getHomeInfo(userId);
            return ResponseEntity.ok(home);

        }catch (Exception e) {
            //TODO : EXCEPTION 처리 필요
            System.out.println(e);
            //
        };
        return null;
    }
}
