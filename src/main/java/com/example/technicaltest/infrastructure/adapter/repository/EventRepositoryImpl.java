package com.example.technicaltest.infrastructure.adapter.repository;

import com.example.technicaltest.domain.model.Event;
import com.example.technicaltest.domain.port.out.EventRepositoryPort;
import com.example.technicaltest.infrastructure.adapter.entity.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepositoryPort {
    private final EventReactiveRepository repository;

    @Override
    public Mono<Event> save(Event event) {
        return repository.save(EventEntity.toEntity(event))
                .map(EventEntity::toModel);
    }

    @Override
    public Mono<Event> update(Event event) {
        return repository.save(EventEntity.toEntity(event))
                .map(EventEntity::toModel);
    }

    @Override
    public Mono<Event> getById(String id) {
        return repository.findById(id)
                .map(EventEntity::toModel);
    }

    @Override
    public Flux<Event> getAll() {
        return repository.findAll()
                .map(EventEntity::toModel);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
