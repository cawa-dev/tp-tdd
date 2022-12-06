package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidAvailablesPointsException;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreationTest {

    @InjectMocks
    private DrivingLicenceGenerationService drivingLicenceGenerationService;

    @Mock
    private DrivingLicenceChecker drivingLicenceChecker;

    @Test
    public void drivingLicenceShouldBeCreateIfItHasTwelvePoints() {
        // GIVEN
        final var givenAvailablePoints = 12;
        // WHEN
        final DrivingLicence drivingLicence = drivingLicenceGenerationService
                .generateDrivingLicenceWithPoints(givenAvailablePoints);
        final var drivingLicencePoints = drivingLicence
                .getAvailablePoints();
        // THEN
        assertThat(givenAvailablePoints).isEqualTo(drivingLicencePoints);
        assertThatNoException().isThrownBy(() -> drivingLicenceGenerationService
                .generateDrivingLicenceWithPoints(drivingLicencePoints));
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

    @Test
    void shouldSaveDrivingLicence() {
        // GIVEN
        final var givenRandomId = UUID.randomUUID();
        final String givenSocialSecurityNumberValid = "123456789123456";
        final int givenAvailablePoints = 12;
        final var givenDrivingLicence = DrivingLicence
                .builder()
                .id(givenRandomId)
                .driverSocialSecurityNumber(givenSocialSecurityNumberValid)
                .availablePoints(givenAvailablePoints)
                .build();

        // WHEN & THEN
        assertThatNoException()
                .isThrownBy(() -> drivingLicenceSaverService
                .saveDrivingLience(givenDrivingLicence));
    }

    @Test
    void shouldNotSaveDrivingLicence() {
        // GIVEN
        final var givenRandomId = UUID.randomUUID();
        final String givenSocialSecurityNumberValid = "123456789123456sachanoon";
        final int givenAvailablePoints = 15;
        final var givenDrivingLicence = DrivingLicence
                .builder()
                .id(givenRandomId)
                .driverSocialSecurityNumber(givenSocialSecurityNumberValid)
                .availablePoints(givenAvailablePoints)
                .build();

        // WHEN & THEN
        assertThatExceptionOfType(InvalidDrivingLicenceException.class)
                .isThrownBy(() -> drivingLicenceSaverService
                        .saveDrivingLience(givenDrivingLicence));
    }
}
