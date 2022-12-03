package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;

public class DrivingLicenceGenerationService {
    DrivingLicence generateDrivingLicenceWithTwelvePoints() {
        return DrivingLicence.builder().availablePoints(12).build();
    }

    public DrivingLicence verifyIfSocialSecurityNumberIsGivenWhenWeCreateDrivingLicence(String driverSocialSecurityNumber) {
    }
}
