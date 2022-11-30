package fr.esgi.cleancode.service;

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
    void shouldReturnTrueIfSocialSecurityNumberIsNotNull(){
        //GIVEN
        final String socialNumber = "UwU";
        //WHEN
        Boolean actual = drivingLicenceSave.checkIfSocialSecurityNumberIsNull(socialNumber);
        //THEN
        assertThat(actual).isTrue();
    }

    @Test
    void shouldReturnFalseIfSocialSecurityNumberIsNull(){
        //GIVEN
        final String socialNumber = null;
        //WHEN
        Boolean actual = drivingLicenceSave.checkIfSocialSecurityNumberIsNull(socialNumber);
        //THEN
        assertThat(actual).isFalse();
    }
}
