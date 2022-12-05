package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidAvailablesPointsException;
import fr.esgi.cleancode.model.DrivingLicence;

import java.util.UUID;

public class DrivingLicenceGenerationService {

    public static DrivingLicence constGoodDrivingLicence = DrivingLicence
            .builder()
            .id(UUID.randomUUID())
            .driverSocialSecurityNumber("123456789123456")
            .availablePoints(12)
            .build();

    private final DrivingLicenceChecker drivingLicenceChecker;

    public DrivingLicenceGenerationService(DrivingLicenceChecker drivingLicenceChecker) {
        this.drivingLicenceChecker = drivingLicenceChecker;
    }

    public DrivingLicence generateDrivingLicenceWithPoints(int sourceAvailablePoints) {
        if (sourceAvailablePoints != 12) {
            throw new InvalidAvailablesPointsException("You cannot create an Driving Licence with : " + sourceAvailablePoints + " points");
        }
        return constGoodDrivingLicence;
    }

    public DrivingLicence generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(String sourceDriverSocialSecurityNumber) {
        drivingLicenceChecker.checkSocialSecurityNumberValidity(sourceDriverSocialSecurityNumber);
        return constGoodDrivingLicence;
    }
}
