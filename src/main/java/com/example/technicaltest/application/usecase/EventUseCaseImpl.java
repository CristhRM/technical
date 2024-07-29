package com.example.technicaltest.application.usecase;

import com.example.technicaltest.domain.model.Event;
import com.example.technicaltest.domain.model.User;
import com.example.technicaltest.domain.port.in.EventUseCase;
import com.example.technicaltest.domain.port.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EventUseCaseImpl implements EventUseCase {
    private final EventRepositoryPort port;

    @Override
    public Mono<Event> save(Event event) {
        return port.save(event);
    }

    @Override
    public Mono<Event> update(String id, Event event) {
        return port.update(event);
    }

    @Override
    public Mono<Event> getById(String id) {
        return port.getById(id);
    }

    @Override
    public Flux<Event> getAll() {
        return port.getAll();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return port.deleteById(id);
    }

    @Override
    public Mono<String> registerUser(String idEvent, User user) {
        return null;
    }
}
