package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidAvailablesPointsException;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;

import java.util.UUID;

public class DrivingLicenceGenerationService {

    private final DrivingLicenceChecker drivingLicenceChecker;

    public DrivingLicenceGenerationService(DrivingLicenceChecker drivingLicenceChecker) {
        this.drivingLicenceChecker = drivingLicenceChecker;
    }

    public DrivingLicence generateDrivingLicence(int availablePoints, String sourceDriverSocialSecurityNumber){
        generateDrivingLicenceWithPoints(availablePoints);
        generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(sourceDriverSocialSecurityNumber);
        return DrivingLicence
                .builder()
                .availablePoints(availablePoints)
                .driverSocialSecurityNumber(sourceDriverSocialSecurityNumber)
                .build();
    }

    public DrivingLicence generateDrivingLicenceWithPoints(int sourceAvailablePoints) throws InvalidAvailablesPointsException {
        if (sourceAvailablePoints != 12) {
            throw new InvalidAvailablesPointsException("You cannot create an Driving Licence with : " + sourceAvailablePoints + " points");
        }
        return DrivingLicence
                .builder()
                .availablePoints(12)
                .build();
    }

    public DrivingLicence generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(String sourceDriverSocialSecurityNumber) throws InvalidDriverSocialSecurityNumberException {
        drivingLicenceChecker.checkSocialSecurityNumberValidity(sourceDriverSocialSecurityNumber);
        return DrivingLicence
                .builder()
                .driverSocialSecurityNumber("123456789123456")
                .build();
    }
}
