package com.team7.be.domain.service;

import com.team7.be.domain.controller.response.AvailableScheduleResponse;
import com.team7.be.domain.entity.availableSchedule.AvailableSchedule;
import com.team7.be.domain.repository.AvailableScheduleRepository;
import com.team7.be.domain.service.dto.AvailableScheduleDto;
import com.team7.be.domain.service.dto.AvailableScheduleListDto;
import com.team7.be.global.exception.SaveScheduleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class AvailableScheduleService {
    private final AvailableScheduleRepository availableScheduleRepository;

    @Transactional
    public void saveAvailableSchedule(AvailableScheduleListDto availableScheduleListDto) {
        try {
            List<AvailableSchedule> schedules = availableScheduleListDto.getAvailableScheduleDtoList().stream()
                    .map(schedule -> AvailableSchedule.builder()
                            .groupId(schedule.getGroupId())
                            .availableStartTime(schedule.getAvailableStartTime())
                            .availableEndTime(schedule.getAvailableEndTime())
                            .build())
                    .collect(Collectors.toList());

            availableScheduleRepository.saveAll(schedules);
        } catch (Exception e) {
            throw new SaveScheduleException("저장에 실패하였습니다.");
        }
    }

    public List<AvailableScheduleResponse> getAvailableSchedule(Long groupId) {
        List<AvailableSchedule> schedules = availableScheduleRepository.findByGroupId(groupId);
        return schedules.stream()
                .map(schedule -> AvailableScheduleResponse.builder()
                        .availableStartTime(schedule.getAvailableStartTime())
                        .availableEndTime(schedule.getAvailableEndTime())
                        .build())
                .collect(Collectors.toList());
    }

    public List<AvailableScheduleResponse> getAvailableScheduleList(Long groupId, Long scheduleId) {
        // 그룹 ID와 스케줄 ID를 기반으로 스케줄 목록을 조회
        List<AvailableSchedule> schedules = availableScheduleRepository.findByGroupIdAndScheduleId(groupId, scheduleId);

        // 비어있는 시간대를 찾기 위한 로직
        List<AvailableScheduleResponse> availableTimes = new ArrayList<>();
        LocalDateTime lastEndTime = schedules.get(0).getAvailableStartTime(); // 첫 스케줄 시작 시간을 초기값으로

        for(AvailableSchedule schedule : schedules) {
            if(!lastEndTime.isEqual(schedule.getAvailableStartTime())) {
                // 빈 시간대 발견
                availableTimes.add(AvailableScheduleResponse.builder()
                        .availableStartTime(lastEndTime)
                        .availableEndTime(schedule.getAvailableStartTime())
                        .build());
            }
            lastEndTime = schedule.getAvailableEndTime();
        }

        return availableTimes;
    }

    public AvailableScheduleResponse getAvailableScheduleResult(Long groupId, Long scheduleId) {
        List<AvailableSchedule> schedules = availableScheduleRepository.findByGroupIdAndScheduleId(groupId, scheduleId);

        // 스케줄을 시작 시간 기준으로 정렬
        schedules.sort(Comparator.comparing(AvailableSchedule::getAvailableStartTime));

        for (int i = 0; i < schedules.size() - 1; i++) {
            LocalDateTime endOfCurrentSchedule = schedules.get(i).getAvailableEndTime();
            LocalDateTime startOfNextSchedule = schedules.get(i + 1).getAvailableStartTime();

            // 만약 현재 스케줄의 끝나는 시간과 다음 스케줄의 시작 시간 사이에 간격이 있다면
            if (!endOfCurrentSchedule.isEqual(startOfNextSchedule)) {
                return AvailableScheduleResponse.builder()
                        .availableStartTime(endOfCurrentSchedule)
                        .availableEndTime(startOfNextSchedule)
                        .build();
            }
        }

        return null;  // 혹은 예외처리 가능
    }

}
