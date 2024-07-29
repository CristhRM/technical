package com.example.technicaltest.infrastructure.adapter.entity;

import com.example.technicaltest.domain.model.Event;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("events")
public record EventEntity(
        @Id String id,
        String name,
        List<String> registeredUses,
        LocalDateTime date,
        String location) {

    public static EventEntity toEntity(Event event) {
        return new EventEntity(
                event.id(),
                event.name(),
                event.registeredUses(),
                event.date(),
                event.location());
    }

    public static Event toModel(EventEntity eventEntity) {
        return new Event(
                eventEntity.id,
                eventEntity.name,
                eventEntity.registeredUses,
                eventEntity.date,
                eventEntity.location);
    }
}
