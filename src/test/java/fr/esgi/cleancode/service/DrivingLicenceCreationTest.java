package fr.esgi.cleancode.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreationTest {

    @InjectMocks
    private DrivingLicenceGenerationService drivingLicenceGenerationService;

    @Mock
    private DrivingLicenceChecker drivingLicenceChecker;

    @Test
    void drivingLicenceShouldHaveTwelvePoints() {
        final var drivingLicense = drivingLicenceGenerationService.generateDrivingLicenceWithTwelvePoints();
        final var drivingLicensePoints = drivingLicense.getAvailablePoints();

        assertThat(drivingLicensePoints).isEqualTo(12);
    }

    @Test
    void itShouldNotThrowAnErrorWhenTheSocialSecurityNumberIsValid() {
        final var givenSocialSecurityNumberValid = "123456789123456";
        assertThatNoException().isThrownBy(
                () -> drivingLicenceGenerationService
                        .generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(givenSocialSecurityNumberValid));
    }
}
