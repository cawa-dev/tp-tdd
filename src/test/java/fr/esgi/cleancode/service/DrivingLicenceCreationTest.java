package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidAvailablesPointsException;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceCreationTest {

    // Numbers of points for driving licence correct
    private static final int AVAILABLE_POINTS_VALID = 12;
    // Numbers of points for driving licence Incorrect
    private static final int AVAILABLE_POINTS_INVALID = 15;
    // Social security number for driving licence correct
    private static final String SOCIAL_SECURITY_NUMBER_VALID = "123456789123456";
    // Social security number for driving licence Incorrect
    private static final String SOCIAL_SECURITY_NUMBER_INVALID = "1234567uWu891toto23456";

    @InjectMocks
    private DrivingLicenceGenerationService drivingLicenceGenerationService;

    @Mock
    private DrivingLicenceCheckerService drivingLicenceCheckerService;

    @Mock
    private DrivingLicenceIdGenerationService drivingLicenceIdGenerationService;

    // 2.2
    @Test
    @Description("This test should not throw exception when generate an driving licence with twelve points")
    void should_create_driving_licence_if_available_points_is_twelve() {
        // WHEN
        doNothing()
                .when(drivingLicenceCheckerService).checkAvailablePoints(AVAILABLE_POINTS_VALID);
        // THEN
        assertThatNoException()
                .isThrownBy(
                        () -> drivingLicenceGenerationService
                                .generateDrivingLicence(AVAILABLE_POINTS_VALID, SOCIAL_SECURITY_NUMBER_VALID)
                );
    }

    // 2.2
    @Test
    @Description("This test should throw custom exception when we try to" +
            "generate an driving licence with more than twelve points")
    void should_not_create_driving_licence_if_available_points_is_more_than_twelve() {
        // WHEN
        doThrow(InvalidAvailablesPointsException.class)
                .when(drivingLicenceCheckerService).checkAvailablePoints(AVAILABLE_POINTS_INVALID);
        // THEN
        assertThatExceptionOfType(InvalidAvailablesPointsException.class)
                .isThrownBy(
                        () -> drivingLicenceGenerationService
                                .generateDrivingLicence(AVAILABLE_POINTS_INVALID, SOCIAL_SECURITY_NUMBER_INVALID)
                );
    }

    // 2.3
    @Test
    @Description("This test should not throw exception when social security number is provided and he's valid")
    public void should_not_throw_exception_when_social_security_number_is_valid() {
        // WHEN
        doNothing()
                .when(drivingLicenceCheckerService)
                .checkValidity(SOCIAL_SECURITY_NUMBER_VALID);
        // THEN
        assertThatNoException()
                .isThrownBy(
                        () -> drivingLicenceGenerationService
                                .generateDrivingLicence(AVAILABLE_POINTS_VALID, SOCIAL_SECURITY_NUMBER_VALID)
                );
        verify(drivingLicenceCheckerService, times(1))
                .checkValidity(SOCIAL_SECURITY_NUMBER_VALID);
    }

    // 2.3
    @Test
    @Description("This test should throw an exception when the social security number is provided but invalid")
    public void should_throw_exception_when_social_security_number_is_invalid() {
        // WHEN
        doThrow(InvalidDriverSocialSecurityNumberException.class)
                .when(drivingLicenceCheckerService)
                .checkValidity(SOCIAL_SECURITY_NUMBER_INVALID);
        // THEN
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class)
                .isThrownBy(
                        () -> drivingLicenceGenerationService
                                .generateDrivingLicence(AVAILABLE_POINTS_VALID, SOCIAL_SECURITY_NUMBER_INVALID)
                );
    }
}
