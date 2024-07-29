package com.example.technicaltest.infrastructure.config;

import com.example.technicaltest.application.service.EventService;
import com.example.technicaltest.application.usecase.EventUseCaseImpl;
import com.example.technicaltest.domain.port.out.EventRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public EventService eventService(EventRepositoryPort eventRepositoryPort) {
        return new EventService(new EventUseCaseImpl(eventRepositoryPort));
    }
}
