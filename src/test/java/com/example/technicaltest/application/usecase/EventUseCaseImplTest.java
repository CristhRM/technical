package com.example.technicaltest.application.usecase;

import com.example.technicaltest.domain.port.out.EventRepositoryPort;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class EventUseCaseImplTest {

    @InjectMocks
    private EventUseCaseImpl useCase;

    @Mock
    private EventRepositoryPort eventRepositoryPort;
}
