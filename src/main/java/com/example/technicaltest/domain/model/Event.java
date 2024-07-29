package com.example.technicaltest.domain.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder(toBuilder = true)
public record Event(
        String id,
        String name,
        List<String> registeredUses,
        LocalDateTime date,
        String location) {
}
