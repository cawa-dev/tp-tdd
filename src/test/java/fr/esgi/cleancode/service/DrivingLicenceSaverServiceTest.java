package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidAvailablesPointsException;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.exception.InvalidDrivingLicenceException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceSaverServiceTest {

    @InjectMocks
    private DrivingLicenceSaverService drivingLicenceSaverService;

    @Mock
    private InMemoryDatabase inMemoryDatabase;

    @Mock
    private DrivingLicenceGenerationService drivingLicenceGenerationService;

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
                        .saveDrivingLicence(givenRandomId, givenDrivingLicence));
    }

    @Test
    void shouldNotSaveDrivingLicence() {
        // GIVEN
        final var givenRandomId = UUID.randomUUID();
        final String givenSocialSecurityNumberInvalid = "123456789123456sachanoon";
        final int givenAvailablePoints = 15;
        final var givenDrivingLicence = DrivingLicence
                .builder()
                .id(givenRandomId)
                .driverSocialSecurityNumber(givenSocialSecurityNumberInvalid)
                .availablePoints(givenAvailablePoints)
                .build();
        // WHEN
        when(drivingLicenceGenerationService.generateDrivingLicence(givenAvailablePoints, givenSocialSecurityNumberInvalid))
                .thenThrow(InvalidDrivingLicenceException.class);

        // THEN
        assertThatExceptionOfType(InvalidDrivingLicenceException.class)
                .isThrownBy(() -> drivingLicenceSaverService
                        .saveDrivingLicence(givenRandomId, givenDrivingLicence));
    }
}