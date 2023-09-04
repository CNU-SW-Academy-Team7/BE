package com.team7.be.domain.service;

import com.team7.be.domain.entity.Schedule;
import com.team7.be.domain.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository; // ScheduleRepository 주입

    // 새로운 스케줄 추가
    public Schedule addNewSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // 기존 스케줄 삭제
    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    // 기존 스케줄을 groupId로 탐색
    public Optional<Schedule> findScheduleByGroupId(Long groupId) {
        return scheduleRepository.findByGroupId(groupId);
    }

    // 기존 스케줄을 memberId로 탐색
    public Optional<Schedule> findScheduleByMemberIdAndGroupId(Long groupId, Long memberId) {
        return scheduleRepository.findByGroupIdAndMemberId(groupId, memberId);
    }

    // 기존 스케줄을 시작 기간, 끝나는 기간으로 검색
    public List<Schedule> findScheduleByTimePeriod(Long groupId, LocalDateTime startDate, LocalDateTime endDate) {
        return scheduleRepository.findByGroupIdAndSelectedStartDateIsAfterAndSelectedEndDateIsBefore(groupId, startDate, endDate);
    }

    // 받은 날짜로부터 7일 후까지의 스케줄을 받아오는 메소드
    public List<Schedule> findScheduleForUpcomingWeek(Long groupId, LocalDateTime now) {
        LocalDateTime sevenDaysLater = now.plusDays(7);
        return scheduleRepository.findByGroupIdAndSelectedStartDateIsAfterAndSelectedEndDateIsBeforeOrSelectedEndDateIsNull(groupId, now, sevenDaysLater);
    }
}
