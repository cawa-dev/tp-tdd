package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDrivingLicenceException;
import fr.esgi.cleancode.model.DrivingLicence;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceReturnTest {

    @InjectMocks
    private DrivingLicenceCheckReturnService drivingLicenceCheckReturnService;

    @Mock
    private DrivingLicenceSaverService drivingLicenceSaverService;

    // 2.5
    @Test
    @Description("This test should return the driving licence when he's saved")
    public void should_return_driving_licence_when_it_saved() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        final var givenSocialSecurityNumber = "123456789123456";
        final var givenAvailablePoints = 12;
        // WHEN
        final DrivingLicence givenDrivingLicence = DrivingLicence
                .builder()
                .id(givenId)
                .availablePoints(givenAvailablePoints)
                .driverSocialSecurityNumber(givenSocialSecurityNumber)
                .build();

        // WHEN
        DrivingLicence drivingLicenceSaved = drivingLicenceCheckReturnService
                .checkReturn(givenId, givenDrivingLicence);
        // THEN
        assertThat(drivingLicenceSaved).isEqualTo(givenDrivingLicence);
    }

    // 2.5
    @Test
    @Description("This test should throw an exception and " +
            "do not return the driving licence when she's invalid")
    public void should_not_return_when_save_is_invalid() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        final var givenSocialSecurityNumber = "uwu";
        final var givenAvailablePoints = 15;
        // WHEN
        final DrivingLicence givenDrivingLicence = DrivingLicence
                .builder()
                .id(givenId)
                .availablePoints(givenAvailablePoints)
                .driverSocialSecurityNumber(givenSocialSecurityNumber)
                .build();

        doThrow(InvalidDrivingLicenceException.class)
                .when(drivingLicenceSaverService)
                .saveDrivingLicence(givenId, givenDrivingLicence);

        // THEN
        assertThatExceptionOfType(InvalidDrivingLicenceException.class)
                .isThrownBy(
                        () -> drivingLicenceCheckReturnService
                                .checkReturn(givenId, givenDrivingLicence)
                );
    }

}