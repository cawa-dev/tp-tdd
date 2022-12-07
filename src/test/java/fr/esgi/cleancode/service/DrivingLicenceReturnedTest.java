package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatNoException;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceReturnedTest {

    @InjectMocks
    private DrivingLicenceCheckReturnService drivingLicenceCheckReturnService;

    @Mock
    private DrivingLicenceSaverService drivingLicenceSaverService;

    @Test
    public void shouldReturnDrvingLicencObjectWhenGenerated() {
        //Retourner le nouveau permis de conduire
        // GIVEN
        final var nbPoint = 12;
        final var secu = "123456789123456";
        final UUID id = UUID.randomUUID();
        final DrivingLicence randomDrivingLicence = DrivingLicence.builder().id(id).availablePoints(nbPoint).driverSocialSecurityNumber(secu).build();
        // WHEN
        // THEN
        assertThatNoException().isThrownBy(() -> drivingLicenceCheckReturnService.checkReturn(id, randomDrivingLicence));
    }

}