package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidAvailablesPointsException;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreationTest {

    @InjectMocks
    private DrivingLicenceGenerationService drivingLicenceGenerationService;

    @Mock
    private DrivingLicenceChecker drivingLicenceChecker;

    @Test
    public void drivingLicenceShouldBeCreateIfItHasTwelvePoints() {
        final var givenAvailablePoints = 12;
        assertThatNoException()
                .isThrownBy(() -> drivingLicenceGenerationService
                        .generateDrivingLicenceWithPoints(givenAvailablePoints));
    }

    @Test
    public void drivingLicenceShouldNotBeCreateIfItHasNotTwelvePoints() {
        final var givenAvailablePoints = 15;
        assertThatExceptionOfType(InvalidAvailablesPointsException.class)
                .isThrownBy(() -> drivingLicenceGenerationService
                        .generateDrivingLicenceWithPoints(givenAvailablePoints));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "czeqfze", "123456"})
    public void itShouldThrowAnExceptionWhenTheSocialSecurityNumberIsInvalid(String givenSecurityNumberInvalid) {
        // STUBBER
        doThrow(InvalidDriverSocialSecurityNumberException.class)
                .when(drivingLicenceChecker)
                .checkSocialSecurityNumberValidity(givenSecurityNumberInvalid);
        // WHEN
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceGenerationService
                        .generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(givenSecurityNumberInvalid));
        verifyNoMoreInteractions(drivingLicenceChecker);
    }

    @ParameterizedTest
    @ValueSource(strings = {"546128761354972", "123456789123456"})
    public void itShouldNotThrowAnExceptionWhenTheSocialSecurityNumberIsValid(String givenSecurityNumberValid) {
        // STUBBER
        doNothing()
                .when(drivingLicenceChecker)
                .checkSocialSecurityNumberValidity(givenSecurityNumberValid);
        // WHEN
        drivingLicenceGenerationService
                .generateDrivingLicenceWhenSocialSecurityNumberIsProvidedAndItHasBeenChecked(givenSecurityNumberValid);
        verifyNoMoreInteractions(drivingLicenceChecker);
    }

}
