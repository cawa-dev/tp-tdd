package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DrivingLicenceGenerationService {

    private final DrivingLicenceChecker drivingLicenceChecker;

    public DrivingLicence generateDrivingLicenceWithTwelvePoints() {
        return DrivingLicence.builder().availablePoints(12).build();
    }

    public DrivingLicence generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(String givenDriverSocialSecurityNumber) {
        drivingLicenceChecker.checkSocialSecurityNumberValidity(givenDriverSocialSecurityNumber);
        return DrivingLicence.builder()
                .id(UUID.randomUUID())
                .driverSocialSecurityNumber(givenDriverSocialSecurityNumber)
                .availablePoints(12)
                .build();
    }
}
