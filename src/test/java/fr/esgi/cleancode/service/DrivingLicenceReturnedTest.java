package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDrivingLicenceException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceReturnedTest {

    @InjectMocks
    private DrivingLicenceCheckReturnService drivingLicenceCheckReturnService;

    @Mock
    private DrivingLicenceSaverService drivingLicenceSaverService;

    @Test
    public void shouldReturnDrivingLicencWhenIsSaved() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        final var givenSocialSecuriteNumber = "123456789123456";
        final var givenAvailablePoints = 12;
        final DrivingLicence randomDrivingLicence = DrivingLicence
                .builder()
                .id(givenId)
                .availablePoints(givenAvailablePoints)
                .driverSocialSecurityNumber(givenSocialSecuriteNumber)
                .build();
        // WHEN
        // THEN
        assertThatNoException()
                .isThrownBy(() -> drivingLicenceCheckReturnService
                .checkReturn(givenId, randomDrivingLicence));
    }

    @Test
    public void shouldThrowEcptionWhileTryingToSaveDrivingLicence() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        final var givenSocialSecuriteNumber = "uwu";
        final var givenAvailablePoints = 15;
        final DrivingLicence randomDrivingLicence = DrivingLicence
                .builder()
                .id(givenId)
                .availablePoints(givenAvailablePoints)
                .driverSocialSecurityNumber(givenSocialSecuriteNumber)
                .build();
        // WHEN
        // THEN
        assertThatExceptionOfType(InvalidDrivingLicenceException.class)
                .isThrownBy(() -> drivingLicenceCheckReturnService
                .checkReturn(givenId, randomDrivingLicence));
    }

}