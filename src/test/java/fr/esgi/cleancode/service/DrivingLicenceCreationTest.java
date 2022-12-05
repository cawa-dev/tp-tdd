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

    @ParameterizedTest
    @ValueSource(strings = {"", "czeqfze", "123456"})
    public void itShouldThrowAnExceptionWhenTheSocialSecurityNumberIsInvalid(String source) {
        // STUB
        doThrow(InvalidDriverSocialSecurityNumberException.class)
                .when(drivingLicenceChecker)
                .checkSocialSecurityNumberValidity(source);
        // WHEN
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceGenerationService
                        .generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(source));
    }

    @ParameterizedTest
    @ValueSource(strings = {"546128761354972", "123456789123456"})
    public void itShouldNotThrowAnExceptionWhenTheSocialSecurityNumberIsValid(String source) {
        // STUB
        doNothing()
                .when(drivingLicenceChecker)
                .checkSocialSecurityNumberValidity(source);
        // WHEN
        drivingLicenceGenerationService
                .generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(source);
    }

}
