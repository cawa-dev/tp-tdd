package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DrivingLicenceRemoveService {
    private final DrivingLicenceFinderService drivingLicenceFinderService;

    public int removePoints(UUID givenId, int pointsToRemoveFromDrivingLicence) {
        final var drivingLicence = drivingLicenceFinderService
                .findById(givenId)
                .orElse(
                        DrivingLicence
                                .builder()
                                .id(givenId)
                                .availablePoints(12)
                                .build()
                );

        final var drivingLicencePoints = drivingLicence.getAvailablePoints();

        return drivingLicencePoints - pointsToRemoveFromDrivingLicence;
    }
}
