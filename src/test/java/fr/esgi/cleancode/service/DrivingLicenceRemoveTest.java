package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidAvailablesPointsException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
        final var givenAvailablePoints = 12;
        final var generatedDrivingLicence = DrivingLicence
                .builder()
                .id(givenId)
                .availablePoints(givenAvailablePoints)
                .build();
        // WHEN
        final var drivingLicenceAfter = drivingLicenceRemoveService
                .removePoints(generatedDrivingLicence, pointsToRemoveFromDrivingLicence);
        final int drivingLicenceAvailablePointsAfterRemove = drivingLicenceAfter.getAvailablePoints();
        // THEN
        assertThat(drivingLicenceAvailablePointsAfterRemove)
                .isEqualTo(givenAvailablePoints - pointsToRemoveFromDrivingLicence);
    }

    @Test
    void shouldNotRemovePointsIfDrivingLicencePointsIsLessThanZero() {
        // GIVEN
        final var pointsToRemoveFromDrivingLicence = 12;
        final var givenId = UUID.randomUUID();
        final var givenAvailablePoints = 2;
        final var generatedDrivingLicence = DrivingLicence
                .builder()
                .id(givenId)
                .availablePoints(givenAvailablePoints)
                .build();

        // WHEN & THEN
        assertThatExceptionOfType(InvalidAvailablesPointsException.class)
                .isThrownBy(() -> drivingLicenceRemoveService
                        .removePoints(generatedDrivingLicence, pointsToRemoveFromDrivingLicence));
    }

    @Test
    void shouldUpdateDrivingLicenceInDatabase () {
        // GIVEN
        final var pointsToRemoveFromDrivingLicence = 2;
        final var givenId = UUID.randomUUID();
        final var givenAvailablePoints = 12;
        final var generatedDrivingLicence = DrivingLicence
                .builder()
                .id(givenId)
                .availablePoints(givenAvailablePoints)
                .build();
        // WHEN

        // remove points to a driving licence and store it into an variable to test it
        final var drivingLicenceAfter = drivingLicenceRemoveService
                .removePoints(generatedDrivingLicence, pointsToRemoveFromDrivingLicence);
        final int drivingLicenceAvailablePointsAfterRemove = drivingLicenceAfter.getAvailablePoints();

        // update the driving licence in the database to check it
        drivingLicenceRemoveService.updateDrivingLicence(givenId, generatedDrivingLicence);

        // THEN
        assertThat(generatedDrivingLicence).isEqualTo(drivingLicenceAfter);
    }

}
