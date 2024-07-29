package com.example.technicaltest.application.service;

import com.example.technicaltest.domain.model.Event;
import com.example.technicaltest.domain.model.User;
import com.example.technicaltest.domain.port.in.EventUseCase;
import com.example.technicaltest.infrastructure.exception.CustomNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class EventServiceTest {
    @InjectMocks
    private EventService eventService;

    @Mock
    private EventUseCase useCase;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    void saveEventTest() {
        Event event = Event.builder().build();

        when(useCase.save(any(Event.class))).thenReturn(Mono.just(event));

        Mono<Event> eventSaved = eventService.save(event);

        StepVerifier.create(eventSaved)
                .expectNext(event)
                .verifyComplete();
    }

    @Test
    void updateEventTest() {
        Event event = Event.builder().build();

        when(useCase.getById(anyString())).thenReturn(Mono.just(event));
        when(useCase.update(anyString(), any(Event.class))).thenReturn(Mono.just(event));

        Mono<Event> eventUpdate = eventService.update("Event01", event);

        StepVerifier.create(eventUpdate)
                .expectNext(event)
                .verifyComplete();
    }

    @Test
    void updateEventTestWhenEventNotExist() {
        Event event = Event.builder().build();

        when(useCase.getById(anyString())).thenReturn(Mono.empty());
        when(useCase.update(anyString(), any(Event.class))).thenReturn(Mono.just(event));

        Mono<Event> eventUpdate = eventService.update("Event01", event);

        StepVerifier.create(eventUpdate)
                .expectErrorMatches(throwable -> throwable instanceof CustomNotFoundException)
                .verify();
    }

    @Test
    void getEventByIdTest() {
        Event event = Event.builder().build();

        when(useCase.getById(anyString())).thenReturn(Mono.just(event));

        Mono<Event> eventSaved = eventService.getById("Event01");

        StepVerifier.create(eventSaved)
                .expectNext(event)
                .verifyComplete();
    }

    @Test
    void getEventByIdTestWhenEventNoExist() {
        when(useCase.getById(anyString())).thenReturn(Mono.empty());

        Mono<Event> eventSaved = eventService.getById("Event01");

        StepVerifier.create(eventSaved)
                .expectErrorMatches(throwable -> throwable instanceof CustomNotFoundException)
                .verify();
    }

    @Test
    void getAllEventsTest() {
        Event event1 = Event.builder().build();

        when(useCase.getAll()).thenReturn(Flux.just(event1));

        Flux<Event> eventSaved = eventService.getAll();

        StepVerifier.create(eventSaved)
                .expectNext(event1)
                .verifyComplete();
    }

    @Test
    void deleteEventByIdTest() {
        when(useCase.deleteById(anyString())).thenReturn(Mono.empty());

        Mono<Void> eventSaved = eventService.deleteById("Event01");

        StepVerifier.create(eventSaved)
                .verifyComplete();
    }

    @Test
    void registerUserInEventTest() {
        User user = new User("user01");
        Event event = Event.builder().build();

        when(useCase.getById(anyString())).thenReturn(Mono.just(event));
        when(useCase.save(any(Event.class))).thenReturn(Mono.just(event));

        Mono<String> response = eventService.registerUser("Event01", user);

        StepVerifier.create(response)
                .expectNext("User registered successful in event: Event01")
                .verifyComplete();
    }

    @Test
    void registerUserInEventTestWhenEvenNotExist() {
        User user = new User("user01");

        when(useCase.getById(anyString())).thenReturn(Mono.empty());

        Mono<String> response = eventService.registerUser("Event01", user);

        StepVerifier.create(response)
                .expectErrorMatches(throwable -> throwable instanceof CustomNotFoundException)
                .verify();
    }
}
