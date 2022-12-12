package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidAvailablesPointsException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DrivingLicenceRemoveService {

    private final DrivingLicenceSaverService drivingLicenceSaverService;

    public DrivingLicence removePoints(DrivingLicence drivingLicence, int pointsToRemoveFromDrivingLicence) {
        final var drivingLicencePoints = drivingLicence.getAvailablePoints();
        final UUID drivingLicenceId = drivingLicence.getId();

        if (drivingLicencePoints < pointsToRemoveFromDrivingLicence) {
            throw new InvalidAvailablesPointsException("You cannot remove more points than existing !");
        }

        final var drivingLicencePointsAfterRemoved = drivingLicencePoints - pointsToRemoveFromDrivingLicence;

        final DrivingLicence drivingLicenceUpdated = drivingLicence
                .withAvailablePoints(drivingLicencePointsAfterRemoved);

        drivingLicenceSaverService.saveDrivingLicence(drivingLicenceId, drivingLicenceUpdated);

        return drivingLicenceUpdated;
    }
}
