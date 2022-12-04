package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;

import java.util.UUID;

public class DrivingLicenceGenerationService {

    private final DrivingLicenceChecker drivingLicenceChecker;

    public DrivingLicenceGenerationService(DrivingLicenceChecker drivingLicenceChecker) {
        this.drivingLicenceChecker = drivingLicenceChecker;
    }

    public DrivingLicence generateDrivingLicenceWithTwelvePoints() {
        return DrivingLicence.builder().availablePoints(12).build();
    }

    public DrivingLicence generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(String driverSocialSecurityNumber) {
        drivingLicenceChecker.checkSocialSecurityNumberValidity(driverSocialSecurityNumber);
        return DrivingLicence.builder()
                .id(UUID.randomUUID())
                .driverSocialSecurityNumber(driverSocialSecurityNumber)
                .availablePoints(12)
                .build();
    }
}
