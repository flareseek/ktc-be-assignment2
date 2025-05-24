package com.ktc.schedule.controller;

import com.ktc.schedule.dto.PasswordDto;
import com.ktc.schedule.dto.ScheduleRequestDto;
import com.ktc.schedule.dto.ScheduleResponseDto;
import com.ktc.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> create(@RequestBody ScheduleRequestDto dto) {
        ScheduleResponseDto res = service.create(dto);
        return ResponseEntity.status(201).body(res);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> list(
            @RequestParam(required = false) String modifiedAt,
            @RequestParam(required = false) String author) {
        return ResponseEntity.ok(service.findAll(modifiedAt, author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getOne(@PathVariable int id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(
            @PathVariable int id,
            @RequestBody ScheduleRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable int id,
            @RequestBody PasswordDto dto) {
        service.delete(id, dto);
        return ResponseEntity.noContent().build();
    }
}