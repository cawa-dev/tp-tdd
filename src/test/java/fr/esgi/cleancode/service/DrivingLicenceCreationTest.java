package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreationTest {

    @Mock
    InMemoryDatabase inMemoryDatabase;

    @InjectMocks
    DrivingLicenceGenerationService drivingLicenceGenerationService;

    @InjectMocks
    DrivingLicenceIdGenerationService drivingLicenceIdGenerationService;

    @Captor
    ArgumentCaptor<DrivingLicence> availablePoints;


    @Test
    void drivingLicenceShouldHaveTwelvePoints() {
        // GIVEN
        final var drivingLicense = drivingLicenceGenerationService.generateDrivingLicenceWithTwelvePoints();
        // WHEN
        final var drivingLicensePoints = drivingLicense.getAvailablePoints();
        // THEN
        assertThat(drivingLicensePoints).isEqualTo(12);
    }
}
