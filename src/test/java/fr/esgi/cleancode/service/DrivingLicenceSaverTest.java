package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDrivingLicenceException;
import fr.esgi.cleancode.model.DrivingLicence;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceSaverTest {

    @InjectMocks
    private DrivingLicenceSaverService drivingLicenceSaverService;

    @Mock
    private DrivingLicenceGenerationService drivingLicenceGenerationService;

    @Mock
    private InMemoryDatabase inMemoryDatabase;

    // 2.4
    @Test
    @Description("This test should save driving licence when every parameters is correct")
    void should_save_driving_licence() {
        // GIVEN
        final var givenRandomId = UUID.randomUUID();
        final var givenSocialSecurityNumberValid = "123456789123456";
        final var givenAvailablePoints = 12;
        // WHEN
        final var givenDrivingLicence = DrivingLicence
                .builder()
                .id(givenRandomId)
                .driverSocialSecurityNumber(givenSocialSecurityNumberValid)
                .availablePoints(givenAvailablePoints)
                .build();

        // THEN
        assertThatNoException()
                .isThrownBy(
                        () -> drivingLicenceSaverService
                                .saveDrivingLicence(givenRandomId, givenDrivingLicence)
                );
    }

    // 2.4
    @Test
    @Description("This test should not save driving licence when parameters are not correct")
    void should_not_save_driving_licence() {
        // GIVEN
        final var givenRandomId = UUID.randomUUID();
        final var givenSocialSecurityNumberInvalid = "123456789123456sachanoon";
        final var givenAvailablePoints = 15;
        // WHEN
        final var givenDrivingLicence = DrivingLicence
                .builder()
                .id(givenRandomId)
                .driverSocialSecurityNumber(givenSocialSecurityNumberInvalid)
                .availablePoints(givenAvailablePoints)
                .build();
        // WHEN
        when(drivingLicenceGenerationService
                .generateDrivingLicence(givenAvailablePoints, givenSocialSecurityNumberInvalid))
                .thenThrow(
                        new InvalidDrivingLicenceException("The driving licence who your trying to save is invalid : " + givenDrivingLicence + " !")
                );

        // THEN
        assertThatExceptionOfType(InvalidDrivingLicenceException.class)
                .isThrownBy(
                        () -> drivingLicenceSaverService
                                .saveDrivingLicence(givenRandomId, givenDrivingLicence)
                );
    }
}