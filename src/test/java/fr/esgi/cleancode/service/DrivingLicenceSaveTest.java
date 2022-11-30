package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
public class DrivingLicenceSaveTest {

    @InjectMocks
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
