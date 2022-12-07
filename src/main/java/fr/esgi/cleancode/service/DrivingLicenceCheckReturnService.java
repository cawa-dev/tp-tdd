package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDrivingLicenceException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DrivingLicenceCheckReturnService {
    private final DrivingLicenceSaverService drivingLicenceSaverService;

    public DrivingLicence checkReturn(UUID id, DrivingLicence randomDrivingLicence) {
        drivingLicenceSaverService.saveDrivingLicence(id, randomDrivingLicence);
        return randomDrivingLicence;
    }
}
