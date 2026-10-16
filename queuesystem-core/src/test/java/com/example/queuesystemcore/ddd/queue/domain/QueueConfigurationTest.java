package com.example.queuesystemcore.ddd.queue.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueConfigurationTest {

    @Test
    void shouldReturnNextNumberWhenCurrentNumberIsBelowUpperBound() {
        QueueConfiguration configuration = configuration(6, 1, 999);

        assertEquals(7, configuration.getNextNumber());
        assertEquals(6, configuration.getCurrentNumber(),
                "Getting the next number should not itself persist/update currentNumber.");
    }

    @Test
    void shouldRestartAtLowerBoundWhenCurrentNumberReachedUpperBound() {
        QueueConfiguration configuration = configuration(999, 1, 999);

        assertEquals(1, configuration.getNextNumber());
        assertEquals(0, configuration.getCurrentNumber(),
                "The current implementation resets currentNumber to numberFrom - 1 before returning the next number.");
    }

    @Test
    void shouldResetCounterToOneBelowLowerBound() {
        QueueConfiguration configuration = configuration(123, 10, 999);

        configuration.restartCurrentNumber();

        assertEquals(9, configuration.getCurrentNumber());
        assertEquals(10, configuration.getNextNumber());
    }

    @Test
    void shouldConvertConfigurationToDto() {
        QueueConfiguration configuration = new QueueConfiguration(
                42L,
                "3f82f7f0-0a17-4fc1-b1f0-48f991b1f2a8",
                "General queue",
                17L,
                6,
                1,
                999,
                "A"
        );

        var dto = configuration.toDto();

        assertEquals("3f82f7f0-0a17-4fc1-b1f0-48f991b1f2a8",
                dto.getQueueConfigurationUUID().toString());
        assertEquals("General queue", dto.getConfigurationDescription());
        assertEquals("1 - 999", dto.getNumberRange());
        assertEquals("A", dto.getSign());
    }

    private QueueConfiguration configuration(int current, int from, int to) {
        return new QueueConfiguration(
                42L,
                "3f82f7f0-0a17-4fc1-b1f0-48f991b1f2a8",
                "General queue",
                17L,
                current,
                from,
                to,
                "A"
        );
    }
}
