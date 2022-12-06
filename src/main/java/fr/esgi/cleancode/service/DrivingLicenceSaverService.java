package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDrivingLicenceException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DrivingLicenceSaverService {
    private final InMemoryDatabase inMemoryDatabase;
    private final DrivingLicenceGenerationService drivingLicenceGenerationService;
    public void saveDrivingLicence(UUID sourceId, DrivingLicence sourceDrivingLicence)
            throws InvalidDrivingLicenceException {
        final int availablePoints = sourceDrivingLicence.getAvailablePoints();
        final String socialSecurityNumber = sourceDrivingLicence.getDriverSocialSecurityNumber();

        drivingLicenceGenerationService.generateDrivingLicence(availablePoints, socialSecurityNumber);

        inMemoryDatabase.save(sourceId, sourceDrivingLicence);
    }
}
