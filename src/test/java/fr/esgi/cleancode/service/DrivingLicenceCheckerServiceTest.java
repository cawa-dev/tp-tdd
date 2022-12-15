package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCheckerServiceTest {

    @InjectMocks
    DrivingLicenceCheckerService drivingLicenceCheckerService;

    // 1.1
    @Test
    @Description("This test should return true if the String passed in parameter of the method is not equals to null")
    void should_return_true_if_not_null() {
        final var givenSocialSecurityNumberNotNull = "UwU";
        final var actual = drivingLicenceCheckerService.checkNullability(givenSocialSecurityNumberNotNull);
        assertThat(actual).isNotNull();
    }

    // 1.1
    @Test
    @Description("This test should throw an exception if the String passed in parameter of the method is equals to null")
    void should_throw_if_null() {
        final String givenSocialSecurityNumberNull = null;
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceCheckerService
                        .checkNullability(givenSocialSecurityNumberNull));
    }

    // 1.2
    @Test
    @Description("This test should return true if the String passed in parameter of the method contain only digit")
    void should_contain_only_numbers() {
        final var givenSocialSecurityNumberWithNumbersOnly = "123456789";
        var actual = drivingLicenceCheckerService
                .checkContainsOnlyNumbers(givenSocialSecurityNumberWithNumbersOnly);
        assertThat(actual).isTrue();
    }

    // 1.2
    @Test
    @Description("This test should throw an exception if the " +
            "String passed in parameter of the method contain do not contains only numbers")
    void should_throw_if_not_only_numbers() {
        final var givenInvalidSocialSecurityNumberWithLettersIn = "bla123456789blabla";
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(() -> drivingLicenceCheckerService.checkContainsOnlyNumbers(givenInvalidSocialSecurityNumberWithLettersIn));
    }

    // 1.3
    @Test
    @Description("This test should return true if there is fifteen numbers in the String passed in the parameter of the method")
    void should_return_true_if_fifteen_numbers() {
        final var givenSecuritySocialNumberWithFifteenNumbers = "123456789123456";
        final boolean actual = drivingLicenceCheckerService
                .checkLength(givenSecuritySocialNumberWithFifteenNumbers);
        assertThat(actual).isTrue();
    }

    // 1.3
    @Test
    @Description("This test should throw an exception if there " +
            "is more than fifteen numbers in the String passed in parameter of the method")
    void should_throw_if_contain_more_than_fifteen_numbers() {
        final var givenSecuritySocialNumberWithMoreThanFifteenNumbers = "123456789123456878655565";
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(
                        () -> drivingLicenceCheckerService
                                .checkLength(givenSecuritySocialNumberWithMoreThanFifteenNumbers)
                );
    }

    // 1.4
    @Test
    @Description("This test should not throw exception if the social security number is valid")
    void should_not_throw_if_social_security_number_is_valid() {
        final var givenSocialSecurityNumberValid = "123456789123456";
        assertThatNoException()
                .isThrownBy(
                        () -> drivingLicenceCheckerService
                                .checkValidity(givenSocialSecurityNumberValid)
                );
    }

    // 1.4
    @Test
    @Description("This test should throw exception if the social security number isn't valid")
    void should_throw_if_social_security_number_not_valid() {
        final var givenSocialSecurityNumberInvalid = "UwU54186541651UwU";
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(
                        () -> drivingLicenceCheckerService
                                .checkValidity(givenSocialSecurityNumberInvalid)
                );

    }
}