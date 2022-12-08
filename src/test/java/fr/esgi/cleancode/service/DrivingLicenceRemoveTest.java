package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceRemoveTest {

    @InjectMocks
    private DrivingLicenceFinderService drivingLicenceFinderService;

    @Mock
    private InMemoryDatabase inMemoryDatabase;

    @Test
    void shouldThrowWhenDrivingLicenceIsNotFound() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        // WHEN
        when(inMemoryDatabase.findById(givenId))
                .thenThrow(ResourceNotFoundException.class);
        // THEN
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(()-> drivingLicenceFinderService
                        .findById(givenId));
    }

    @Test
    void shouldNotThrownWhenDrivingLicenceIsFound() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        drivingLicenceFinderService.findById(givenId);
        // WHEN & THEN
        assertThatNoException()
                .isThrownBy(()-> drivingLicenceFinderService
                        .findById(givenId));
    }
}
