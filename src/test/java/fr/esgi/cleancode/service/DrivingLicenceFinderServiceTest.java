package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceFinderServiceTest {

    @InjectMocks
    private DrivingLicenceFinderService service;

    @Mock
    private InMemoryDatabase database;

    @Test
    void shouldFindDrivingLicenceById() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        // WHEN
        when(database
                .findById(givenId)
                .get());

        Optional<DrivingLicence> optionalDrivingLicence = service.findById(givenId);
        // THEN
        assertThat(optionalDrivingLicence).isPresent();
    }

    @Test
    void shouldNotFindDrivingLicenceById() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        // WHEN
        when(database
                .findById(givenId)
                .orElseThrow(ResourceNotFoundException.class));
        // THEN
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> service.findById(givenId));
    }
}