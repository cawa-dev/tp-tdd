package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DrivingLicenceGenerationService {

    private final DrivingLicenceCheckerService drivingLicenceCheckerService;

    private final DrivingLicenceIdGenerationService drivingLicenceIdGenerationService;

    public DrivingLicence generateDrivingLicence(int availablePoints, String socialSecurityNumber) {
        final var id = drivingLicenceIdGenerationService.generateNewDrivingLicenceId();
        drivingLicenceCheckerService.checkAvailablePoints(availablePoints);
        drivingLicenceCheckerService.checkValidity(socialSecurityNumber);
        return DrivingLicence
                .builder()
                .id(id)
                .driverSocialSecurityNumber(socialSecurityNumber)
                .availablePoints(availablePoints)
                .build();
    }
}
