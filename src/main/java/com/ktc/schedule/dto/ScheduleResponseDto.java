package com.ktc.schedule.dto;

import java.time.LocalDateTime;

public class ScheduleResponseDto {
    private final Integer id;
    private final String todo;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleResponseDto(Integer id, String todo, String author, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.todo = todo;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public String getTodo() {
        return todo;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
