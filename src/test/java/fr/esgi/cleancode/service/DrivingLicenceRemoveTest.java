package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceRemoveTest {

    @Test
    void shouldRemovePointFromDrivingLicence() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        final var drivingLicence = drivingLicenceFinderService.findById(givenId);
        final var drivingLicencePoints = drivingLicence.get().getAvailablePoints();
        final var pointsToRemoveFromDrivingLicence = 2;

        // WHEN
        drivingLicenceRemoveService.removePoints(givenId, pointsToRemoveFromDrivingLicence);

        // THEN
        final var drivingLicencePointsAfterRemovingPoints =
                drivingLicenceFinderService.findById(givenId).get().getAvailablePoints();


        assertThat(drivingLicencePoints - pointsToRemoveFromDrivingLicence)
                .isEqualTo(drivingLicencePointsAfterRemovingPoints);
    }

}
