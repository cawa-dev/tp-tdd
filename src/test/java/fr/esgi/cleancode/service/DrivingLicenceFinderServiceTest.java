package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceFinderServiceTest {

    @InjectMocks
    private DrivingLicenceFinderService drivingLicenceFinderService;

    @Mock
    private InMemoryDatabase inMemoryDatabase;

    @Test
    void shouldFindDrivingLicenceById() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        final var givenDrivingLicence = DrivingLicence
                .builder()
                .id(givenId)
                .build();
        // WHEN
        when(inMemoryDatabase.findById(givenId)).thenReturn(Optional.of(givenDrivingLicence));
        final Optional<DrivingLicence> optionalDrivingLicence = drivingLicenceFinderService.findById(givenId);
        // THEN
        assertThat(optionalDrivingLicence).containsSame(givenDrivingLicence);
        verify(inMemoryDatabase).findById(givenId);
        verifyNoMoreInteractions(inMemoryDatabase);
    }

    @Test
    void shouldNotFindDrivingLicenceById() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        /*
            refactoring this to make it nullable and not empty because
            we may not have empty object in the program
        */
        // WHEN
        when(inMemoryDatabase.findById(givenId)).thenReturn(Optional.empty());
        final Optional<DrivingLicence> optionalDrivingLicence = drivingLicenceFinderService.findById(givenId);
        // THEN
        assertThat(optionalDrivingLicence).isEmpty();
        verify(inMemoryDatabase).findById(givenId);
        verifyNoMoreInteractions(inMemoryDatabase);
    }

    @Test
    void shouldThrowWhenDrivingLicenceIsNotFound() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        // WHEN
        when(inMemoryDatabase.findById(givenId))
                .thenThrow(new ResourceNotFoundException("Driving Licence with id : " + givenId + " not found !"));
        // THEN
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> drivingLicenceFinderService
                        .findById(givenId));
    }

    @Test
    void shouldNotThrowWhenDrivingLicenceIsFound() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        drivingLicenceFinderService.findById(givenId);
        // WHEN & THEN
        assertThatNoException()
                .isThrownBy(() -> drivingLicenceFinderService
                        .findById(givenId));
    }
}