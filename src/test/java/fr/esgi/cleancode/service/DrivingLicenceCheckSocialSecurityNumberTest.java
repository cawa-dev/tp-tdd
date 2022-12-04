package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import org.junit.jupiter.api.Disabled;
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
    void shouldReturnIfSocialSecurityNumberIsNotNull() {
        final var givenSocialSecurityNumberNotNull = "UwU";
        final var actual = drivingLicenceChecker.checkIfSocialSecurityNumberIsNull(givenSocialSecurityNumberNotNull);
        assertThat(actual).isTrue();
    }

    @Test
    void shouldThrowExceptionIfSocialSecurityNumberIsNull() {
        final String givenSocialSecurityNumberNull = null;
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceChecker
                        .checkIfSocialSecurityNumberIsNull(givenSocialSecurityNumberNull));
    }

    @Test
    void shouldContainsOnlyNumbersInSocialSecurityNumber() {
        String givenSocialSecurityNumberWithNumbersOnly = "123456789";
        Boolean actual = drivingLicenceChecker
                .checkIfSocialSecurityNumberContainsOnlyNumbers(givenSocialSecurityNumberWithNumbersOnly);
        assertThat(actual).isTrue();
    }

    @Test
    void shouldThrowExceptionIfNotContainsOnlyNumbersInSocialSecurityNumber() {
        final var givenInvalidSocialSecurityNumberWithLettersIn = "bla123456789blabla";
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceChecker.checkIfSocialSecurityNumberContainsOnlyNumbers(givenInvalidSocialSecurityNumberWithLettersIn));
    }

    @Test
    void shouldReturnTrueIfContainsFifteenNumbers() {
        final var givenSecuritySocialNumberWithFifteenNumbers = "123456789123456";
        final boolean actual = drivingLicenceChecker
                .checkIfSocialSecurityNumberContainsFifteenNumbers(givenSecuritySocialNumberWithFifteenNumbers);
        assertThat(actual).isTrue();
    }

    @Test
    void shouldThrowExceptionIfItDoNotContainsFifteenNumbers() {
        final var givenSecuritySocialNumberWithMoreThanFifteenNumbers = "123456789123456878655565";
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceChecker
                        .checkIfSocialSecurityNumberContainsFifteenNumbers(givenSecuritySocialNumberWithMoreThanFifteenNumbers));
    }

    @Test
    void shouldThrowInvalidDriverSocialSecurityNumberExceptionIfSocialSecurityNumberIsInvalid() {
        final var givenSocialSecurityNumberInvalid = "UwU54186541651UwU";
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceChecker
                        .checkSocialSecurityNumberValidity(givenSocialSecurityNumberInvalid));

    }

    @Test()
    void shouldNotThrowInvalidDriverSocialSecurityNumberExceptionIfSocialSecurityNumberIsValid() {
        final var givenSocialSecurityNumberValid = "123456789123456";
        final var actual = drivingLicenceChecker
                .checkSocialSecurityNumberValidity(givenSocialSecurityNumberValid);
        assertDoesNotThrow(() -> drivingLicenceChecker
                .checkSocialSecurityNumberValidity(givenSocialSecurityNumberValid));
        assertThat(actual).isTrue();
    }
}