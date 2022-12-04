package fr.esgi.cleancode.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceIdGenerationServiceTest {

    @InjectMocks
    private DrivingLicenceIdGenerationService service;

    @Test
    void should_generate_valid_UUID() {
        final var actual = service.generateNewDrivingLicenceId();
        assertThat(actual)
                .isNotNull()
                .isEqualTo(UUID.fromString(actual.toString()));
    }
}