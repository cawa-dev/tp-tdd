package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrivingLicenceGenerationService {
    private final DrivingLicenceChecker drivingLicenceChecker;
    DrivingLicence generateDrivingLicenceWithTwelvePoints() {
        return DrivingLicence.builder().availablePoints(12).build();
    }

     DrivingLicence generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(String driverSocialSecurityNumber) {
         if (!drivingLicenceChecker.checkSocialSecurityNumberValidity(driverSocialSecurityNumber)) {
             throw new InvalidDriverSocialSecurityNumberException("The Security Social Number provided is not valid or it wasn't checked : " + driverSocialSecurityNumber);
         }
         return DrivingLicence.builder().driverSocialSecurityNumber(driverSocialSecurityNumber).availablePoints(12).build();
     }
}
