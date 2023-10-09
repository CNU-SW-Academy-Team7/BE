package com.team7.be.domain.service;

import com.team7.be.domain.controller.response.HomeResponse;
import com.team7.be.domain.entity.Schedule;
import com.team7.be.domain.repository.MemberRepository;
import com.team7.be.domain.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class HomeService {
    private final ScheduleRepository scheduleRepository;
    public HomeResponse getHome(Long userId) {
        List<Schedule> schedule = scheduleRepository.findByMemberId_userId(userId);
        LocalDateTime Max_date = LocalDateTime.MAX;
        LocalDateTime today=LocalDateTime.now(TimeZone.getDefault().toZoneId());
        String groupName="";
        String scheduleName="";
        if (!schedule.isEmpty()) {
            for(Schedule s:schedule){
                var date=s.getSelectedStartDate();
                if(date.isBefore(Max_date) && date.isAfter(today)){
                    Max_date=date;
                    groupName=s.getUserGroupId().getGroupName();
                    scheduleName=s.getScheduleName();
                }
            }
            return HomeResponse.builder()
                    .groupName(groupName)
                    .date(Max_date)
                    .scheduleName(scheduleName)
                    .build();
        }
        else{
            return null;
        }
    }

}
