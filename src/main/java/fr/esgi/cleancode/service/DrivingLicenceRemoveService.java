package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrivingLicenceRemoveService {
    private final DrivingLicenceFinderService drivingLicenceFinderService;

    public DrivingLicence removePoints(DrivingLicence drivingLicence, int pointsToRemoveFromDrivingLicence) {
        final var drivingLicencePoints = drivingLicence.getAvailablePoints();
        final var drivingLicencePointsAfterRemoved = drivingLicencePoints - pointsToRemoveFromDrivingLicence;
        return drivingLicence.withAvailablePoints(drivingLicencePointsAfterRemoved);
    }
}
