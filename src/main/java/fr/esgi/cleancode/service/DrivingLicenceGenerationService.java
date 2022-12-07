package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidAvailablesPointsException;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrivingLicenceGenerationService {

    private final DrivingLicenceChecker drivingLicenceChecker;

    protected DrivingLicence generateDrivingLicence(int availablePoints, String socialSecurityNumber) {
        generateDrivingLicenceWithPoints(availablePoints);
        generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(socialSecurityNumber);
        return DrivingLicence
                .builder()
                .availablePoints(availablePoints)
                .driverSocialSecurityNumber(socialSecurityNumber)
                .build();
    }

    protected void generateDrivingLicenceWithPoints(int sourceAvailablePoints) throws InvalidAvailablesPointsException {
        if (sourceAvailablePoints != 12) {
            throw new InvalidAvailablesPointsException("You cannot create an Driving Licence with : " + sourceAvailablePoints + " points");
        }
    }

    protected void generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(String sourceDriverSocialSecurityNumber) throws InvalidDriverSocialSecurityNumberException {
        drivingLicenceChecker.checkSocialSecurityNumberValidity(sourceDriverSocialSecurityNumber);
    }
}
