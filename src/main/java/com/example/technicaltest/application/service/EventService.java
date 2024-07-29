package com.example.technicaltest.application.service;

import com.example.technicaltest.domain.model.Event;
import com.example.technicaltest.domain.model.User;
import com.example.technicaltest.domain.port.in.EventUseCase;
import com.example.technicaltest.infrastructure.exception.CustomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService implements EventUseCase {
    private final EventUseCase useCase;

    @Override
    public Mono<Event> save(Event event) {
        return useCase.save(event);
    }

    @Override
    public Mono<Event> update(String id, Event event) {
        return useCase.getById(id)
                .switchIfEmpty(Mono.error(new CustomNotFoundException("Event no found with id: " + id)))
                .flatMap(findEvent -> useCase.save(event));
    }

    @Override
    public Mono<Event> getById(String id) {
        return useCase.getById(id)
                .switchIfEmpty(Mono.error(new CustomNotFoundException("Event no found with id: " + id)));
    }

    @Override
    public Flux<Event> getAll() {
        return useCase.getAll();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return useCase.deleteById(id);
    }

    @Override
    public Mono<String> registerUser(String idEvent, User user) {
        return useCase.getById(idEvent)
                .switchIfEmpty(Mono.error(new CustomNotFoundException("Event no found with id: " + idEvent)))
                .flatMap(event -> {
                    List<String> users = event.registeredUses();
                    if (users != null) {
                        users.add(user.userId());
                        event.toBuilder()
                                .registeredUses(users)
                                .build();
                        return useCase.save(event);
                    }

                    Event updateEvent = Event.builder()
                            .id(event.id())
                            .name(event.name())
                            .registeredUses(List.of(user.userId()))
                            .date(event.date())
                            .location(event.location())
                            .build();
                    return useCase.save(updateEvent);
                })
                .then(Mono.just("User registered successful in event: " + idEvent));
    }
}
