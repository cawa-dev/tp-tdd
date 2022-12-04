package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;

import java.util.UUID;

public class DrivingLicenceGenerationService {

    private final DrivingLicenceChecker drivingLicenceChecker;

    public DrivingLicenceGenerationService(DrivingLicenceChecker drivingLicenceChecker) {
        this.drivingLicenceChecker = drivingLicenceChecker;
    }

    public DrivingLicence generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(String givenDriverSocialSecurityNumber)
            throws InvalidDriverSocialSecurityNumberException {
        drivingLicenceChecker.checkSocialSecurityNumberValidity(givenDriverSocialSecurityNumber);
        return DrivingLicence.builder()
                .id(UUID.randomUUID())
                .driverSocialSecurityNumber(givenDriverSocialSecurityNumber)
                .availablePoints(12)
                .build();
    }

    public DrivingLicence generateDrivingLicenceWithTwelvePoints() {
        return DrivingLicence.builder().availablePoints(12).build();
    }
}
