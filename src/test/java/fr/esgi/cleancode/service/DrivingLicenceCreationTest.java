package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreationTest {

    @InjectMocks
    private DrivingLicenceGenerationService drivingLicenceGenerationService;

    @Mock
    private DrivingLicenceChecker drivingLicenceChecker;

    @Test
    public void drivingLicenceShouldHaveTwelvePoints() {
        // GIVEN
        final var drivingLicense = drivingLicenceGenerationService.generateDrivingLicenceWithTwelvePoints();
        // WHEN
        final var drivingLicensePoints = drivingLicense.getAvailablePoints();
        // THEN
        assertThat(drivingLicensePoints).isEqualTo(12);
    }
    @Test
    public void itShouldThrowAnExceptionWhenTheSocialSecurityNumberIsInvalid() {
        // GIVEN
        final var givenInvalidSocialSecurityNumber = "totosachanoesarah1234567";
        // WHEN
        drivingLicenceGenerationService.generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(givenInvalidSocialSecurityNumber);
        verify(drivingLicenceChecker, atLeastOnce())
                .checkSocialSecurityNumberValidity(givenInvalidSocialSecurityNumber);
        // THEN
        assertThatException()
                .isThrownBy(() -> drivingLicenceGenerationService
                        .generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(givenInvalidSocialSecurityNumber));
        verifyNoMoreInteractions(drivingLicenceChecker);
    }

    @Test
    @Disabled
    public void itShouldNotThrowAnExceptionWhenTheSocialSecurityNumberIsValid(String sourceDriverSocialSecurityNumber) {
        // GIVEN
        // WHEN
        // THEN
    }

}
