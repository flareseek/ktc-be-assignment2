package com.ktc.schedule.repository;

import com.ktc.schedule.model.Schedule;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ScheduleRepository {
    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert insert;

    public ScheduleRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.insert = new SimpleJdbcInsert(jdbc)
                .withTableName("schedules")
                .usingGeneratedKeyColumns("id");
    }

    public Schedule save(Schedule schedule) {
        Map<String, Object> params = Map.of(
                "todo",       schedule.getTodo(),
                "author",     schedule.getAuthor(),
                "password",   schedule.getPassword(),
                "created_at", schedule.getCreatedAt(),
                "updated_at", schedule.getUpdatedAt()
        );
        Number key = insert.executeAndReturnKey(params);
        schedule.setId(key.intValue());
        return schedule;
    }

    public List<Schedule> findAll(LocalDate modifiedAt, String author) {
        StringBuilder sql = new StringBuilder("SELECT * FROM schedules");
        List<Object> args = new ArrayList<>();

        if (modifiedAt != null) {
            sql.append(" WHERE DATE(updated_at)=?");
            args.add(modifiedAt);
        }
        if (author != null) {
            sql.append(args.isEmpty() ? " WHERE" : " AND")
                    .append(" author=?");
            args.add(author);
        }
        sql.append(" ORDER BY updated_at DESC");

        return jdbc.query(
                sql.toString(),
                args.toArray(),
                new BeanPropertyRowMapper<>(Schedule.class)
        );
    }

    public Optional<Schedule> findById(int id) {
        List<Schedule> list = jdbc.query(
                "SELECT * FROM schedules WHERE id=?",
                new BeanPropertyRowMapper<>(Schedule.class),
                id
        );
        return list.stream().findFirst();
    }
}
