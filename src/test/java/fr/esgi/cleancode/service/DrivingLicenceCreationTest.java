package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreationTest {

    @Mock
    InMemoryDatabase inMemoryDatabase;

    @InjectMocks
    DrivingLicenceGenerationService drivingLicenceGenerationService;

    @InjectMocks
    DrivingLicenceIdGenerationService drivingLicenceIdGenerationService;

    @Test
    void drivingLicenceShouldHaveTwelvePoints() {
        // GIVEN
        final var drivingLicense = drivingLicenceGenerationService.generateDrivingLicenceWithTwelvePoints();
        // WHEN
        final var drivingLicensePoints = drivingLicense.getAvailablePoints();
        // THEN
        assertThat(drivingLicensePoints).isEqualTo(12);
    }

    @Test
    void drivingLicenceHasSecuritySocialNumberWhenWeCreated(){
        // GIVEN
        final var drivingLicense = drivingLicenceGenerationService.verifyIfSocialSecurityNumberIsGivenWhenWeCreateDrivingLicence("pnl");
        // WHEN
        final var drivingLicenseSocialSecurityNumber = drivingLicense.getDriverSocialSecurityNumber();
        // THEN
        assertThat(drivingLicenseSocialSecurityNumber).isNotNull();
    }
}
