package com.example.technicaltest.domain.port.in;

import com.example.technicaltest.domain.model.Event;
import com.example.technicaltest.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventUseCase {
    Mono<Event> save(Event event);
    Mono<Event> update(String id, Event event);
    Mono<Event> getById(String id);
    Flux<Event> getAll();
    Mono<Void> deleteById(String id);
    Mono<String> registerUser(String idEvent, User user);
}
