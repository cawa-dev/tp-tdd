package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceRemoveTest {

    @Mock
    DrivingLicenceFinderService drivingLicenceFinderService;

    @InjectMocks
    DrivingLicenceRemoveService drivingLicenceRemoveService;

    @Test
    void shouldRemovePointFromDrivingLicence() {
        // GIVEN
        final var pointsToRemoveFromDrivingLicence = 2;
        final var givenId = UUID.randomUUID();
        final var generatedDrivingLicence = DrivingLicence
                .builder()
                .id(givenId)
                .driverSocialSecurityNumber("123456789123456")
                .availablePoints(12)
                .build();
        final var givenDrivingLicence = drivingLicenceFinderService
                .findById(givenId)
                .orElse(generatedDrivingLicence);

        drivingLicenceRemoveService.removePoints(givenId, pointsToRemoveFromDrivingLicence);
        Optional<DrivingLicence> drivingLicenceById = drivingLicenceFinderService
                .findById(givenId);

        assertThat(drivingLicenceById).isNotEqualTo(givenDrivingLicence);

    }

}
