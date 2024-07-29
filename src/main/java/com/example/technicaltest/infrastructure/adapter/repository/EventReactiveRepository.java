package com.example.technicaltest.infrastructure.adapter.repository;

import com.example.technicaltest.infrastructure.adapter.entity.EventEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EventReactiveRepository extends ReactiveMongoRepository<EventEntity, String> {
}
