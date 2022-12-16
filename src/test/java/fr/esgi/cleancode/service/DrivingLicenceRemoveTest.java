package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidAvailablesPointsException;
import fr.esgi.cleancode.model.DrivingLicence;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceRemoveTest {

    @Mock
    DrivingLicenceFinderService drivingLicenceFinderService;

    @Mock
    DrivingLicenceSaverService drivingLicenceSaverService;

    @InjectMocks
    DrivingLicenceRemoveService drivingLicenceRemoveService;

    // 1.2.1
    @Test
    @Description("This test should remove points from the driving licence")
    void should_remove_point_from_driving_licence() {
        // GIVEN
        final var pointsToRemoveFromDrivingLicence = 2;
        final var givenId = UUID.randomUUID();
        final var givenAvailablePoints = 12;
        // WHEN
        final var generatedDrivingLicence = DrivingLicence
                .builder()
                .id(givenId)
                .availablePoints(givenAvailablePoints)
                .build();
        final var drivingLicenceAfter = drivingLicenceRemoveService
                .removePoints(generatedDrivingLicence, pointsToRemoveFromDrivingLicence);
        final int drivingLicenceAvailablePointsAfterRemove = drivingLicenceAfter.getAvailablePoints();
        // THEN
        assertThat(drivingLicenceAvailablePointsAfterRemove)
                .isEqualTo(givenAvailablePoints - pointsToRemoveFromDrivingLicence);
    }

    // 1.2.1
    @Test
    @Description("This test should not remove points if available points is less than zero")
    void should_not_remove_point_from_driving_licence() {
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
                .isThrownBy(
                        () -> drivingLicenceRemoveService
                                .removePoints(generatedDrivingLicence, pointsToRemoveFromDrivingLicence)
                );
    }

    // 1.2.2
    @Test
    @Description("This test should update the driving licence when remove points")
    void should_remove_points_from_driving_licence_and_update() {
        // GIVEN
        final var pointsToRemoveFromDrivingLicence = 2;
        final var givenId = UUID.randomUUID();
        final var givenAvailablePoints = 12;
        // WHEN
        final var generatedDrivingLicence = DrivingLicence
                .builder()
                .id(givenId)
                .availablePoints(givenAvailablePoints)
                .build();
        final var drivingLicenceAfter = drivingLicenceRemoveService
                .removePoints(generatedDrivingLicence, pointsToRemoveFromDrivingLicence);
        // THEN
        assertThat(generatedDrivingLicence).isNotEqualTo(drivingLicenceAfter);
    }
}
