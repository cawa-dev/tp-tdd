package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;
@RequiredArgsConstructor
public class DrivingLicenceRemoveService {
    private  final DrivingLicenceFinderService drivingLicenceFinderService;
    public int removePoints(UUID givenId, int pointsToRemoveFromDrivingLicence) {
        final Optional<DrivingLicence> drivingLicence = drivingLicenceFinderService.findById(givenId);
        return drivingLicence
                .stream()
                .mapToInt(DrivingLicence::getAvailablePoints)
                .reduce(12, (a, b) -> a - b);
 }
}
