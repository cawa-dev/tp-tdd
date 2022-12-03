package fr.esgi.cleancode.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreationTest {

    @Mock
    DrivingLicenceChecker drivingLicenceChecker;

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
    void socialSecurityNumberValidShouldBeProvidedWhenWeSaveIt(){
        final var securitySocialNumber = "123456789123456";

        assertThatNoException().isThrownBy(
                () -> drivingLicenceGenerationService
                        .generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(securitySocialNumber));
    }
}
