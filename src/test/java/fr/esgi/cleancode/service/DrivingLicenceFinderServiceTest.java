package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
        final var givenDrivingLicence = DrivingLicence
                .builder()
                .id(givenId)
                .build();
        // WHEN
        when(database.findById(givenId)).thenReturn(Optional.of(givenDrivingLicence));
        final Optional<DrivingLicence> optionalDrivingLicence = service.findById(givenId);
        // THEN
        assertThat(optionalDrivingLicence).containsSame(givenDrivingLicence);
        verify(database).findById(givenId);
        verifyNoMoreInteractions(database);
    }

    @Test
    void shouldNotFindDrivingLicenceById() {
        // GIVEN
        final var givenId = UUID.randomUUID();
        /*
            refactoring this to make it nullable and not empty because
            we may not have empty object in the program
        */
        // WHEN
        when(database.findById(givenId)).thenReturn(Optional.empty());
        final Optional<DrivingLicence> optionalDrivingLicence = service.findById(givenId);
        // THEN
        assertThat(optionalDrivingLicence).isEmpty();
        verify(database).findById(givenId);
        verifyNoMoreInteractions(database);
    }
}