package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

public class DrivingLicenceSaveTest {

    @Mock
    DrivingLicenceSave drivingLicenceSave;

    @Test
    void shouldSaveDrivingLicenceWithGoodSocialNumber(){
        //GIVEN
        final String socialNumber = "UwU";
        final var drivingLicence =  DrivingLicence.builder().driverSocialSecurityNumber(socialNumber).build();
        //WHEN
        Boolean saving = drivingLicenceSave.save(drivingLicence);
        //THEN
        assertThat(saving).isTrue();
    }

    @Test
    void shouldNotSaveDrivingLicenceWhenSocialSecurityNumberIsNull(){
        //GIVEN
        final String socialNumber = null;
        final var drivingLicence =  DrivingLicence.builder().driverSocialSecurityNumber(socialNumber).build();
        //WHEN
        Boolean saving = drivingLicenceSave.save(drivingLicence);
        //THEN
        assertThat(saving).isFalse();
    }
}
