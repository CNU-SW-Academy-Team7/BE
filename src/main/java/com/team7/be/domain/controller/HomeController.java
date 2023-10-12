package com.team7.be.domain.controller;

import com.team7.be.domain.controller.response.home.HomeResponse;
import com.team7.be.domain.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class HomeController {
    private final HomeService HomeService;
    @GetMapping("/home/{userId}")
    public ResponseEntity<HomeResponse> getHome(@PathVariable Long userId) {
        HomeResponse home = HomeService.getHome(userId);
        return ResponseEntity.ok(home);
    }
}
