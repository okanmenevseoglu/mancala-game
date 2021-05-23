package io.github.okanmenevseoglu.mancalagameapi.service.rules.validation;

import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A unit test class that contains the tests of the {@link GameOverValidationRule} class.
 */
@ExtendWith(MockitoExtension.class)
class GameOverValidationRuleTest {

    @InjectMocks
    private GameOverValidationRule rule;

    @Test
    void shouldValidateGameOverRule() {
        Board board = new Board();

        assertThat(rule.isValidated(board, null)).isTrue();

        board.setStonesForGivenPitIndex(6, 36);
        board.setStonesForGivenPitIndex(13, 36);

        assertThat(rule.isValidated(board, null)).isFalse();
    }

    @Test
    void shouldGetMessage() {
        Board board = new Board();

        assertThat(rule.getMessage(board)).isEqualTo("GAME OVER!");
    }
}
