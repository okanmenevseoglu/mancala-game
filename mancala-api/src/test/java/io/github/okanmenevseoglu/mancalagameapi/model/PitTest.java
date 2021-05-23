package io.github.okanmenevseoglu.mancalagameapi.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * A unit test class that contains the tests of the {@link Pit} class.
 */
class PitTest {

    @Test
    void shouldCreatePits() {
        Pit pit = new Pit();

        assertThat(pit.getStones()).isEqualTo(6);

        pit = new Pit(0);

        assertThat(pit.getStones()).isEqualTo(0);

        pit.setStones(3);
        assertThat(pit.getStones()).isEqualTo(3);
    }

    @Test
    void shouldThrowExceptionIfStonesAreLessThanZero() {
        Pit pit = new Pit(5);

        try {
            pit.setStones(-1);
            fail("Stones should be greater than 0!");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Stones cannot be less than 0!");
        }

        try {
            new Pit(-1);
            fail("Stones should be greater than 0!");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Stones cannot be less than 0!");
        }
    }
}
