package io.github.okanmenevseoglu.mancalagameapi.service.rules.gameplay;

import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import io.github.okanmenevseoglu.mancalagameapi.service.rules.MancalaGameRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A unit test class that contains the tests of the {@link MancalaGameRule} class. However, the most of the functionalities
 * are tested by the integration test class of the game.
 */
@ExtendWith(MockitoExtension.class)
class SowRuleTest {

    @InjectMocks
    private SowRule rule;

    @Test
    void shouldCheckIfNextMoveInHomePitForPlayers() {
        Board board = new Board();

        assertThat(rule.isNextMoveInHomePit(0, board.getPlayer1())).isFalse();
        assertThat(rule.isNextMoveInHomePit(1, board.getPlayer1())).isFalse();
        assertThat(rule.isNextMoveInHomePit(2, board.getPlayer1())).isFalse();
        assertThat(rule.isNextMoveInHomePit(3, board.getPlayer1())).isFalse();
        assertThat(rule.isNextMoveInHomePit(4, board.getPlayer1())).isFalse();
        assertThat(rule.isNextMoveInHomePit(5, board.getPlayer1())).isTrue();
        assertThat(rule.isNextMoveInHomePit(6, board.getPlayer1())).isFalse();
        assertThat(rule.isNextMoveInHomePit(7, board.getPlayer1())).isFalse();
        assertThat(rule.isNextMoveInHomePit(8, board.getPlayer1())).isFalse();
        assertThat(rule.isNextMoveInHomePit(9, board.getPlayer1())).isFalse();
        assertThat(rule.isNextMoveInHomePit(10, board.getPlayer1())).isFalse();
        assertThat(rule.isNextMoveInHomePit(11, board.getPlayer1())).isFalse();
        assertThat(rule.isNextMoveInHomePit(12, board.getPlayer1())).isFalse();

        assertThat(rule.isNextMoveInHomePit(0, board.getPlayer2())).isFalse();
        assertThat(rule.isNextMoveInHomePit(1, board.getPlayer2())).isFalse();
        assertThat(rule.isNextMoveInHomePit(2, board.getPlayer2())).isFalse();
        assertThat(rule.isNextMoveInHomePit(3, board.getPlayer2())).isFalse();
        assertThat(rule.isNextMoveInHomePit(4, board.getPlayer2())).isFalse();
        assertThat(rule.isNextMoveInHomePit(5, board.getPlayer2())).isFalse();
        assertThat(rule.isNextMoveInHomePit(7, board.getPlayer2())).isFalse();
        assertThat(rule.isNextMoveInHomePit(8, board.getPlayer2())).isFalse();
        assertThat(rule.isNextMoveInHomePit(9, board.getPlayer2())).isFalse();
        assertThat(rule.isNextMoveInHomePit(10, board.getPlayer2())).isFalse();
        assertThat(rule.isNextMoveInHomePit(11, board.getPlayer2())).isFalse();
        assertThat(rule.isNextMoveInHomePit(12, board.getPlayer2())).isTrue();
        assertThat(rule.isNextMoveInHomePit(13, board.getPlayer2())).isFalse();
    }

    @Test
    void shouldGetWinMessage() {
        Board board = new Board();

        board.moveAllPlayerOneStonesToHomePit();
        board.moveAllPlayerTwoStonesToHomePit();

        assertThat(rule.getWinMessage(board)).isEqualTo("GAME OVER! The game has ended in a DRAW!");

        board.setStonesForGivenPitIndex(6, 35);
        assertThat(rule.getWinMessage(board)).isEqualTo("GAME OVER! Player TWO has WON!");

        board.setStonesForGivenPitIndex(13, 34);
        assertThat(rule.getWinMessage(board)).isEqualTo("GAME OVER! Player ONE has WON!");
    }
}
