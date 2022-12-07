package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
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
        final var socialSecurityNumber = sourceDrivingLicence.getDriverSocialSecurityNumber();

        try {
            drivingLicenceGenerationService.generateDrivingLicence(availablePoints, socialSecurityNumber);
        } catch (InvalidDriverSocialSecurityNumberException exception) {
            exception.printStackTrace();
        }

        inMemoryDatabase.save(sourceId, sourceDrivingLicence);
    }
}
