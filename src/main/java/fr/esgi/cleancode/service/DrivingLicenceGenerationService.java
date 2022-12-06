package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidAvailablesPointsException;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrivingLicenceGenerationService {

    private final DrivingLicenceChecker drivingLicenceChecker;

    protected DrivingLicence generateDrivingLicence(int availablePoints, String socialSecurityNumber){
        generateDrivingLicenceWithPoints(availablePoints);
        generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(socialSecurityNumber);
        return DrivingLicence
                .builder()
                .availablePoints(availablePoints)
                .driverSocialSecurityNumber(socialSecurityNumber)
                .build();
    }

    protected DrivingLicence generateDrivingLicenceWithPoints(int sourceAvailablePoints) throws InvalidAvailablesPointsException {
        if (sourceAvailablePoints != 12) {
            throw new InvalidAvailablesPointsException("You cannot create an Driving Licence with : " + sourceAvailablePoints + " points");
        }
        return DrivingLicence
                .builder()
                .availablePoints(12)
                .build();
    }

    protected DrivingLicence generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(String sourceDriverSocialSecurityNumber) throws InvalidDriverSocialSecurityNumberException {
        drivingLicenceChecker.checkSocialSecurityNumberValidity(sourceDriverSocialSecurityNumber);
        return DrivingLicence
                .builder()
                .driverSocialSecurityNumber("123456789123456")
                .build();
    }
}
