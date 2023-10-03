package com.team7.be.domain.service;

import com.team7.be.domain.controller.response.HomeResponse;
import com.team7.be.domain.entity.Schedule;
import com.team7.be.domain.repository.MemberRepository;
import com.team7.be.domain.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class HomeService {
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    public HomeResponse getHome(Long userId) {
        List<Schedule> schedule = scheduleRepository.findByMemberId(userId);
        LocalDateTime recentDate = LocalDateTime.MAX;
        String groupName="";
        String scheduleName="";
        if (!schedule.isEmpty()) {
            for(Schedule s:schedule){
                var date=s.getSelectedStartDate();
                if(date.isBefore(recentDate)){
                    recentDate=date;
                    groupName=s.getUserGroupId().getGroupName();
                    scheduleName=s.getScheduleName();
                }
            }
            return HomeResponse.builder()
                    .groupName(groupName)
                    .date(recentDate)
                    .scheduleName(scheduleName)
                    .build();
        }
        else{
            return null;
        }
    }

}
