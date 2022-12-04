package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCheckSocialSecurityNumberTest {

    @InjectMocks
    DrivingLicenceChecker drivingLicenceChecker;

    @Test
    void shouldReturnTrueIfSocialSecurityNumberIsNotNull() {
        final String socialNumber = "UwU";
        final var actual = drivingLicenceChecker.checkIfSocialSecurityNumberIsNull(socialNumber);
        assertThat(actual).isTrue();
    }

    @Test
    void shouldThrowExceptionIfSocialSecurityNumberIsNull() {
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceChecker
                        .checkIfSocialSecurityNumberIsNull(null));
    }

    @Test
    void shouldContainsOnlyNumbersInSocialSecurityNumber() {
        String socialSecurityNumberGiven = "123456789";
        Boolean actual = drivingLicenceChecker.checkIfSocialSecurityNumberContainsOnlyNumbers(socialSecurityNumberGiven);
        assertThat(actual).isTrue();
    }

    @Test
    void shouldThrowExceptionIfNotContainsOnlyNumbersInSocialSecurityNumber() {
        final var invalidSocialSecurityNumber = "bla123456789blabla";
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceChecker.checkIfSocialSecurityNumberContainsOnlyNumbers(invalidSocialSecurityNumber));
    }

    @Test
    void shouldReturnTrueIfContainsFifteenNumbers() {
        String securitySocialNumber = "123456789123456";
        Boolean actual = drivingLicenceChecker.checkIfSocialSecurityNumberContainsFifteenNumbers(securitySocialNumber);
        assertThat(actual).isTrue();
    }

    @Test
    void shouldThrowExceptionIfItDoNotContainsFifteenNumbers() {
        final var invalidSecuritySocialNumber = "123456789123456878655565";
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceChecker.checkIfSocialSecurityNumberContainsFifteenNumbers(invalidSecuritySocialNumber));
    }

    @Test
    void shouldThrowInvalidDriverSocialSecurityNumberExceptionIfSocialSecurityNumberIsInvalid() {
        final var securitySocialNumber = "UwU54186541651UwU";
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class).isThrownBy(()
                -> drivingLicenceChecker.checkSocialSecurityNumberValidity(securitySocialNumber));

    }

    @Test()
    void shouldNotThrowInvalidDriverSocialSecurityNumberExceptionIfSocialScurityNumberIsValid() {
        final var securitySocialNumber = "123456789123456";
        boolean actual = drivingLicenceChecker.checkSocialSecurityNumberValidity(securitySocialNumber);
        assertDoesNotThrow(() -> drivingLicenceChecker.checkSocialSecurityNumberValidity(securitySocialNumber));
        assertThat(actual).isTrue();
    }
}