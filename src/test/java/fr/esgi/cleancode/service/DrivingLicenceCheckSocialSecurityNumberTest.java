package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCheckSocialSecurityNumberTest {

    @InjectMocks
    DrivingLicenceChecker drivingLicenceChecker;

    @Test
    public void shouldReturnIfSocialSecurityNumberIsNotNull() {
        final var givenSocialSecurityNumberNotNull = "UwU";
        final var actual = drivingLicenceChecker.checkIfSocialSecurityNumberIsNull(givenSocialSecurityNumberNotNull);
        assertThat(actual).isTrue();
    }

    @Test
    public void shouldThrowExceptionIfSocialSecurityNumberIsNull() {
        final String givenSocialSecurityNumberNull = null;
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceChecker
                        .checkIfSocialSecurityNumberIsNull(givenSocialSecurityNumberNull));
    }

    @Test
    public void shouldContainsOnlyNumbersInSocialSecurityNumber() {
        String givenSocialSecurityNumberWithNumbersOnly = "123456789";
        Boolean actual = drivingLicenceChecker
                .checkIfSocialSecurityNumberContainsOnlyNumbers(givenSocialSecurityNumberWithNumbersOnly);
        assertThat(actual).isTrue();
    }

    @Test
    public void shouldThrowExceptionIfNotContainsOnlyNumbersInSocialSecurityNumber() {
        final var givenInvalidSocialSecurityNumberWithLettersIn = "bla123456789blabla";
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceChecker.checkIfSocialSecurityNumberContainsOnlyNumbers(givenInvalidSocialSecurityNumberWithLettersIn));
    }

    @Test
    public void shouldReturnTrueIfContainsFifteenNumbers() {
        final var givenSecuritySocialNumberWithFifteenNumbers = "123456789123456";
        final boolean actual = drivingLicenceChecker
                .checkIfSocialSecurityNumberContainsFifteenNumbers(givenSecuritySocialNumberWithFifteenNumbers);
        assertThat(actual).isTrue();
    }

    @Test
    public void shouldThrowExceptionIfItDoNotContainsFifteenNumbers() {
        final var givenSecuritySocialNumberWithMoreThanFifteenNumbers = "123456789123456878655565";
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceChecker
                        .checkIfSocialSecurityNumberContainsFifteenNumbers(givenSecuritySocialNumberWithMoreThanFifteenNumbers));
    }

    @Test
    public void shouldThrowInvalidDriverSocialSecurityNumberExceptionIfSocialSecurityNumberIsInvalid() {
        final var givenSocialSecurityNumberInvalid = "UwU54186541651UwU";
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceChecker
                        .checkSocialSecurityNumberValidity(givenSocialSecurityNumberInvalid));

    }

    @Test
    public void shouldNotThrowInvalidDriverSocialSecurityNumberExceptionIfSocialSecurityNumberIsValid() {
        final var givenSocialSecurityNumberValid = "123456789123456";
        assertThatNoException()
                .isThrownBy(() -> drivingLicenceChecker
                        .checkSocialSecurityNumberValidity(givenSocialSecurityNumberValid));
    }
}