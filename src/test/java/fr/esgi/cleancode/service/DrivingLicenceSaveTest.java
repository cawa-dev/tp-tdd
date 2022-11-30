package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void shouldContainsOnlyNumbersInSocialSecurityNumber() {
        // GIVEN
        String socialSecurityNumberGiven = "123456789";
        // WHEN
        Boolean actual = drivingLicenceSave.checkIfSocialSecurityNumberContainsOnlyNumbers(socialSecurityNumberGiven);
        // THEN
        assertThat(actual).isTrue();
    }

    @Test
    void shouldNotContainsOnlyNumbersInSocialSecurityNumber() {
        // GIVEN
        String socialSecurityNumberGiven = "bla123456789blabla";
        // WHEN
        Boolean actual = drivingLicenceSave.checkIfSocialSecurityNumberContainsOnlyNumbers(socialSecurityNumberGiven);
        // THEN
        assertThat(actual).isFalse();
    }

    @Test
    void shouldReturnTrueIfContainsFifteenNumbers(){
        // GIVEN
        String securitySocialNumber = "123456789123456";
        // WHEN
        Boolean actual = drivingLicenceSave.checkIfSocialSecurityNumberContainsFifteenNumbers(securitySocialNumber);
        // THEN
        assertThat(actual).isTrue();
    }
    @Test
    void shouldReturnFalseIfDoNotContainsFifteenNumbers(){
        // GIVEN
        String securitySocialNumber = "123456789123456878655565";
        // WHEN
        Boolean actual = drivingLicenceSave.checkIfSocialSecurityNumberContainsFifteenNumbers(securitySocialNumber);
        // THEN
        assertThat(actual).isFalse();
    }

    @Test
    void shouldThrowInvalidDriverSocialSecurityNumberExceptionIfSocialSecurityNumberIsInvalid(){
        // GIVEN
        String securitySocialNumber = "UwU54186541651UwU";
        // WHEN AND THEN
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class).isThrownBy(()
                -> drivingLicenceSave.checkSocialSecurityNumberValidity(securitySocialNumber));

    }
    @Test()
    void shouldNotThrowInvalidDriverSocialSecurityNumberExceptionIfSocialScurityNumberIsValid(){
        // GIVEN
        String securitySocialNumber = "123456789123456";
        // WHEN AND THEN
        assertThatNoException().isThrownBy(
        () -> drivingLicenceSave.checkSocialSecurityNumberValidity(securitySocialNumber));
    }
}
