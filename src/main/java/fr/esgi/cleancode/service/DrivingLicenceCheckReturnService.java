package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DrivingLicenceCheckReturnService {
    private final DrivingLicenceSaverService drivingLicenceSaverService;

    public DrivingLicence checkReturnedDrivingLicence(UUID id, DrivingLicence drivingLicence) {
        drivingLicenceSaverService.saveDrivingLicence(id, drivingLicence);
        return drivingLicence;
    }
}
