package com.example.technicaltest.infrastructure.controller.dto;

import com.example.technicaltest.domain.model.Event;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record EventRequestDTO(
        String id,
        String name,
        @JsonFormat(pattern = "dd/MM/yyy HH:mm:ss")
        LocalDateTime date,
        String location) {

        public static Event toModel(EventRequestDTO eventDTO) {
                return new Event(
                        eventDTO.id,
                        eventDTO.name,
                        null,
                        eventDTO.date,
                        eventDTO.location);
        }
}
