package io.github.okanmenevseoglu.mancalagameapi.service.rules.validation;

import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A unit test class that contains the tests of the {@link CurrentPlayerNonEmptyPitValidationRule} class.
 */
@ExtendWith(MockitoExtension.class)
class CurrentPlayerNonEmptyPitRuleTest {

    @InjectMocks
    private CurrentPlayerNonEmptyPitValidationRule rule;

    @Test
    void shouldValidateCurrentPlayerNonEmptyPitRule() {
        Board board = new Board();

        assertThat(rule.isValidated(board, -1)).isFalse();
        assertThat(rule.isValidated(board, 6)).isFalse();
        assertThat(rule.isValidated(board, 7)).isFalse();
        assertThat(rule.isValidated(board, 13)).isFalse();


        board.setCurrentPlayer(board.getPlayer2());
        assertThat(rule.isValidated(board, -1)).isFalse();
        assertThat(rule.isValidated(board, 5)).isFalse();
        assertThat(rule.isValidated(board, 6)).isFalse();
        assertThat(rule.isValidated(board, 13)).isFalse();


        board.setStonesForGivenPitIndex(2, 1);
        board.setCurrentPlayer(board.getPlayer1());

        assertThat(rule.isValidated(board, 0)).isTrue();
        assertThat(rule.isValidated(board, 1)).isTrue();
        assertThat(rule.isValidated(board, 2)).isTrue();
        assertThat(rule.isValidated(board, 3)).isTrue();
        assertThat(rule.isValidated(board, 4)).isTrue();
        assertThat(rule.isValidated(board, 5)).isTrue();

        board.setCurrentPlayer(board.getPlayer2());

        assertThat(rule.isValidated(board, 7)).isTrue();
        assertThat(rule.isValidated(board, 8)).isTrue();
        assertThat(rule.isValidated(board, 9)).isTrue();
        assertThat(rule.isValidated(board, 10)).isTrue();
        assertThat(rule.isValidated(board, 11)).isTrue();
        assertThat(rule.isValidated(board, 12)).isTrue();

        board.setStonesForGivenPitIndex(2, 0);

        board.setCurrentPlayer(board.getPlayer1());

        assertThat(rule.isValidated(board, 0)).isTrue();
        assertThat(rule.isValidated(board, 1)).isTrue();

        assertThat(rule.isValidated(board, 2)).isFalse();
    }

    @Test
    void shouldGetMessage() {
        Board board = new Board();

        assertThat(rule.getMessage(board)).isEqualTo("It is Player ONE's turn.");
    }
}
