package fr.esgi.cleancode.service;

import java.util.UUID;

public class DrivingLicenceIdGenerationService {

    public UUID generateNewDrivingLicenceId() {
        return UUID.randomUUID();
    }

}
