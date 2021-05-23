package io.github.okanmenevseoglu.mancalagameapi.dto.converter;

import io.github.okanmenevseoglu.mancalagameapi.dto.response.PitResponse;
import io.github.okanmenevseoglu.mancalagameapi.model.Pit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A unit test class that contains the tests of the {@link PitResponseConverter} class.
 */
@ExtendWith(MockitoExtension.class)
class PitResponseConverterTest {

    @InjectMocks
    private PitResponseConverter pitResponseConverter;

    @Test
    void shouldConvertFromPitToPitResponse() {
        Pit pit = new Pit();

        PitResponse pitResponse = pitResponseConverter.convert(pit);

        assertThat(pitResponse.getStones()).isEqualTo(6);
    }

    @Test
    void shouldThrowNPEIfPitIsNull() {
        try {
            pitResponseConverter.convert(null);
            Assertions.fail("Pit shouldn't be null!");
        } catch (NullPointerException e) {
            assertThat(e.getMessage()).contains("\"pit\" is null");
        }
    }

    @Test
    void shouldConvertFromPitArrayToPitResponseArray() {
        Pit pit1 = new Pit(5);
        Pit pit2 = new Pit(4);
        Pit pit3 = new Pit(6);

        PitResponse[] pitResponses = pitResponseConverter.convertFromArrayToArray(new Pit[]{pit1, pit2, pit3});

        assertThat(pitResponses[0].getStones()).isEqualTo(5);

        assertThat(pitResponses[1].getStones()).isEqualTo(4);

        assertThat(pitResponses[2].getStones()).isEqualTo(6);
    }

    @Test
    void shouldThrowNPEIfEmptyPitExists() {
        Pit pit1 = new Pit(0);
        Pit pit3 = new Pit(2);
        try {
            pitResponseConverter.convertFromArrayToArray(new Pit[]{pit1, null, pit3});
            Assertions.fail("Pit shouldn't be null!");
        } catch (NullPointerException e) {
            assertThat(e.getMessage()).contains("\"pit\" is null");
        }
    }
}
