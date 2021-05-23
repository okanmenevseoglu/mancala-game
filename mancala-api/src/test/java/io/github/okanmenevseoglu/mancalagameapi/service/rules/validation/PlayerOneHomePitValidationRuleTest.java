package io.github.okanmenevseoglu.mancalagameapi.service.rules.validation;

import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A unit test class that contains the tests of the {@link PlayerOneHomePitValidationRule} class.
 */
@ExtendWith(MockitoExtension.class)
class PlayerOneHomePitValidationRuleTest {

    @InjectMocks
    private PlayerOneHomePitValidationRule rule;

    @Test
    void shouldValidatePlayerOneHomePitRule() {
        Board board = new Board();

        assertThat(rule.isValidated(board, 5)).isTrue();
        assertThat(rule.isValidated(board, 6)).isFalse();
    }

    @Test
    void shouldGetMessage() {
        Board board = new Board();

        assertThat(rule.getMessage(board)).isEqualTo("Invalid move! This pit is the home pit of Player ONE!");
    }
}
