package com.example.technicaltest.infrastructure.controller;

import com.example.technicaltest.application.service.EventService;
import com.example.technicaltest.infrastructure.controller.dto.EventDTO;
import com.example.technicaltest.infrastructure.controller.dto.EventRequestDTO;
import com.example.technicaltest.infrastructure.controller.dto.RegisteredResponse;
import com.example.technicaltest.infrastructure.controller.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequestMapping("iasapi/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;

    @PostMapping
    public Mono<ResponseEntity<EventDTO>> saveEvent(@RequestBody EventRequestDTO eventDTO) {
        return service.save(EventRequestDTO.toModel(eventDTO))
                .map(EventDTO::toDTO)
                .map(eventCreated -> new ResponseEntity<>(eventCreated, CREATED));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<EventDTO>> updateEvent(@PathVariable String id, @RequestBody EventRequestDTO requestDTO) {
        return service.update(id, EventRequestDTO.toModel(requestDTO))
                .map(EventDTO::toDTO)
                .map(eventCreated -> new ResponseEntity<>(eventCreated, OK));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<EventDTO>> getEventById(@PathVariable String id) {
        return service.getById(id)
                .map(EventDTO::toDTO)
                .map(findEvent -> new ResponseEntity<>(findEvent, OK));
    }

    @PutMapping("/{id}/register")
    public Mono<ResponseEntity<RegisteredResponse>> registerUserInEvent(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return service.registerUser(id, UserDTO.toModel(userDTO))
                .map(userRegistered -> new ResponseEntity<>(new RegisteredResponse(userRegistered), OK));
    }

    /*@GetMapping
    public Mono<ResponseEntity<List<EventDTO>>> getAllEvents() {
        return service.getAll()
                .collectList()
                .flatMap(EventDTO::toDTO)
                .map(allEvents -> new ResponseEntity<>(allEvents, OK));
    }*/
}
