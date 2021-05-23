package io.github.okanmenevseoglu.mancalagameapi.service.rules.validation;

import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A unit test class that contains the tests of the {@link PlayerTwoHomePitValidationRule} class.
 */
@ExtendWith(MockitoExtension.class)
class PlayerTwoHomePitValidationRuleTest {

    @InjectMocks
    private PlayerTwoHomePitValidationRule rule;

    @Test
    void shouldValidatePlayerTwoHomePitRule() {
        Board board = new Board();

        assertThat(rule.isValidated(board, 12)).isTrue();
        assertThat(rule.isValidated(board, 13)).isFalse();
    }

    @Test
    void shouldGetMessage() {
        Board board = new Board();

        assertThat(rule.getMessage(board)).isEqualTo("Invalid move! This pit is the home pit of Player TWO!");
    }
}
