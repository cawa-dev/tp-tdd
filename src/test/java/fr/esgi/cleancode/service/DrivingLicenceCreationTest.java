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
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
        if (!sourceDriverSocialSecurityNumber.matches("\\d{15}")) {
            doThrow(new InvalidDriverSocialSecurityNumberException("Invalid social security number"))
                    .when(drivingLicenceChecker).checkSocialSecurityNumberValidity(sourceDriverSocialSecurityNumber);
        }
        assertThrows(InvalidDriverSocialSecurityNumberException.class,
                () -> drivingLicenceGenerationService.generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(sourceDriverSocialSecurityNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123sacha4567sarah891noe2345", "123456789123456"})
    public void itShouldNotThrowAnExceptionWhenTheSocialSecurityNumberIsValid(String sourceDriverSocialSecurityNumber) {
        // Check if the given social security number is invalid
        if (!sourceDriverSocialSecurityNumber.matches("\\d{15}")) {
            doThrow(new InvalidDriverSocialSecurityNumberException("Invalid social security number"))
                    .when(drivingLicenceChecker).checkSocialSecurityNumberValidity(sourceDriverSocialSecurityNumber);
        }

        // Ensure that the InvalidDriverSocialSecurityNumberException is not thrown
        assertDoesNotThrow(() -> drivingLicenceGenerationService.generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(sourceDriverSocialSecurityNumber));
    }

}
