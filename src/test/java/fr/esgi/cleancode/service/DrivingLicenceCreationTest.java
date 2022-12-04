package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
    public void drivingLicenceShouldHaveTwelvePoints() {
        final var drivingLicense = drivingLicenceGenerationService.generateDrivingLicenceWithTwelvePoints();
        final var drivingLicensePoints = drivingLicense.getAvailablePoints();

        assertThat(drivingLicensePoints).isEqualTo(12);
    }
    @ParameterizedTest
    @ValueSource(strings = {"123sacha4567sarah891noe2345", "123456789123456"})
    public void itShouldThrowAnExceptionWhenTheSocialSecurityNumberIsInvalid(String sourceDriverSocialSecurityNumber) {
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceGenerationService
                        .generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(sourceDriverSocialSecurityNumber));
    }
}
