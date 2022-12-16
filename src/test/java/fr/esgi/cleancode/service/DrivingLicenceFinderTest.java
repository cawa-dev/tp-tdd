package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import jdk.jfr.Description;
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
class DrivingLicenceFinderTest {

    @InjectMocks
    private DrivingLicenceFinderService drivingLicenceFinderService;

    @Mock
    private InMemoryDatabase inMemoryDatabase;

    // 1.1 & 1.2
    @Test
    @Description("This test should return an optional driving licence when she's found")
    void should_find_driving_licence_by_id() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        final var givenDrivingLicence = DrivingLicence
                .builder()
                .id(givenId)
                .build();
        // WHEN
        when(inMemoryDatabase
                .findById(givenId))
                .thenReturn(Optional.of(givenDrivingLicence));
        final var optionalDrivingLicence = drivingLicenceFinderService.findById(givenId);
        // THEN
        assertThat(optionalDrivingLicence)
                .containsSame(givenDrivingLicence);
        verify(inMemoryDatabase).findById(givenId);
        verifyNoMoreInteractions(inMemoryDatabase);
    }

    // 1.1 & 1.2
    @Test
    @Description("This test should not return an optional driving licence when she's not found")
    void should_not_find_driving_licence_by_id() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        // WHEN
        when(inMemoryDatabase.findById(givenId))
                .thenReturn(Optional.empty());
        final var optionalDrivingLicence = drivingLicenceFinderService
                .findById(givenId);
        // THEN
        assertThat(optionalDrivingLicence)
                .isEmpty();
        verify(inMemoryDatabase)
                .findById(givenId);
        verifyNoMoreInteractions(inMemoryDatabase);
    }

    // 1.1
    @Test
    @Description("This test should not throw exception when an driving licence is found")
    void should_not_throw_when_driving_licence_is_found() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        drivingLicenceFinderService
                .findById(givenId);
        // WHEN & THEN
        assertThatNoException()
                .isThrownBy(
                        () -> drivingLicenceFinderService
                                .findById(givenId)
                );
    }

    // 1.1
    @Test
    @Description("This test should throw exception when an driving licence is not found")
    void should_throw_when_driving_licence_is_not_found() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        // WHEN
        when(inMemoryDatabase
                .findById(givenId))
                .thenThrow(new ResourceNotFoundException("Driving Licence with id : " + givenId + " not found !")
                );
        // THEN
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(
                        () -> drivingLicenceFinderService
                                .findById(givenId)
                );
    }
}