package com.example.technicaltest.domain.port.out;

import com.example.technicaltest.domain.model.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventRepositoryPort {
    Mono<Event> save(Event event);
    Mono<Event> update(Event event);
    Mono<Event> getById(String id);
    Flux<Event> getAll();
    Mono<Void> deleteById(String id);
}
