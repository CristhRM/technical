package com.example.technicaltest.infrastructure.controller.dto;

import com.example.technicaltest.domain.model.Event;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record EventDTO(
        String id,
        String name,
        List<String> registeredUses,
        @JsonFormat(pattern = "dd/MM/yyy HH:mm:ss")
        LocalDateTime date,
        String location) {

    public static EventDTO toDTO(Event event) {
        return new EventDTO(
                event.id(),
                event.name(),
                event.registeredUses(),
                event.date(),
                event.location());
    }
}
