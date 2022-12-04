package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrivingLicenceGenerationService {

    private final DrivingLicenceChecker drivingLicenceChecker;

    public DrivingLicence generateDrivingLicenceWithTwelvePoints() {
        return DrivingLicence.builder().availablePoints(12).build();
    }

     public DrivingLicence generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(String driverSocialSecurityNumber) {
         drivingLicenceChecker.checkSocialSecurityNumberValidity(driverSocialSecurityNumber);
         return DrivingLicence.builder().driverSocialSecurityNumber(driverSocialSecurityNumber).availablePoints(12).build();
     }
}
