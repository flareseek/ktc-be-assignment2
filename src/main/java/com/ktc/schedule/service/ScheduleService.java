package com.ktc.schedule.service;

import com.ktc.schedule.dto.ScheduleRequestDto;
import com.ktc.schedule.dto.ScheduleResponseDto;
import com.ktc.schedule.model.Schedule;
import com.ktc.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final ScheduleRepository repo;

    public ScheduleService(ScheduleRepository repo) {
        this.repo = repo;
    }

    public ScheduleResponseDto create(ScheduleRequestDto dto) {
        LocalDateTime now = LocalDateTime.now();
        Schedule s = new Schedule();
        s.setTodo(dto.getTodo());
        s.setAuthor(dto.getAuthor());
        s.setPassword(dto.getPassword());
        s.setCreatedAt(now);
        s.setUpdatedAt(now);
        Schedule saved = repo.save(s);
        return new ScheduleResponseDto(
                saved.getId(), saved.getTodo(), saved.getAuthor(),
                saved.getCreatedAt(), saved.getUpdatedAt()
        );
    }

    public List<ScheduleResponseDto> findAll(String modifiedAt, String author) {
        LocalDate date = modifiedAt != null ? LocalDate.parse(modifiedAt) : null;
        return repo.findAll(date, author).stream()
                .map(s -> new ScheduleResponseDto(
                        s.getId(), s.getTodo(), s.getAuthor(),
                        s.getCreatedAt(), s.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto findOne(int id) {
        Schedule s = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다."));
        return new ScheduleResponseDto(
                s.getId(), s.getTodo(), s.getAuthor(),
                s.getCreatedAt(), s.getUpdatedAt()
        );
    }
}