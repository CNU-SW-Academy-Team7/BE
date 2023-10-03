package com.team7.be.domain.service;

import com.team7.be.domain.controller.response.HomeResponse;
import com.team7.be.domain.entity.Schedule;
import com.team7.be.domain.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class HomeService {
    private final ScheduleRepository scheduleRepository;

    public HomeResponse getHome(Long userId) {
        Optional<Schedule> schedule = scheduleRepository.findByUserId(userId);
        if (schedule.isPresent()) {
            var groupName=schedule.get().getUserGroupId().getGroupName();
            var date=schedule.get().getSelectedStartDate();
            var scheduleName=schedule.get().getScheduleName();
            return HomeResponse.builder()
                    .groupName(groupName)
                    .date(date)
                    .scheduleName(scheduleName)
                    .build();
        }
        else{
            return null;
        }
    }

}
