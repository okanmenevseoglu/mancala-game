package io.github.okanmenevseoglu.mancalagameapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void shouldGetCurrentPlayer() {
        assertThat(board.getNextPlayer(true).getPlayerName()).isEqualTo(PlayerName.ONE);
        assertThat(board.getNextPlayer(false).getPlayerName()).isEqualTo(PlayerName.TWO);

        board.setCurrentPlayer(board.getPlayer2());

        assertThat(board.getNextPlayer(true).getPlayerName()).isEqualTo(PlayerName.TWO);
        assertThat(board.getNextPlayer(false).getPlayerName()).isEqualTo(PlayerName.ONE);
    }

    @Test
    void shouldSetAndGetStonesForGivenPitIndex() {
        board.setStonesForGivenPitIndex(0, 1);
        assertThat(board.getStonesForGivenPitIndex(0)).isEqualTo(1);

        board.setStonesForGivenPitIndex(1, 2);
        assertThat(board.getStonesForGivenPitIndex(1)).isEqualTo(2);

        board.setStonesForGivenPitIndex(2, 3);
        assertThat(board.getStonesForGivenPitIndex(2)).isEqualTo(3);

        board.setStonesForGivenPitIndex(3, 4);
        assertThat(board.getStonesForGivenPitIndex(3)).isEqualTo(4);

        board.setStonesForGivenPitIndex(4, 5);
        assertThat(board.getStonesForGivenPitIndex(4)).isEqualTo(5);

        board.setStonesForGivenPitIndex(5, 4);
        assertThat(board.getStonesForGivenPitIndex(5)).isEqualTo(4);

        board.setStonesForGivenPitIndex(6, 3);
        assertThat(board.getStonesForGivenPitIndex(6)).isEqualTo(3);

        board.setStonesForGivenPitIndex(7, 2);
        assertThat(board.getStonesForGivenPitIndex(7)).isEqualTo(2);

        board.setStonesForGivenPitIndex(8, 1);
        assertThat(board.getStonesForGivenPitIndex(8)).isEqualTo(1);

        board.setStonesForGivenPitIndex(9, 2);
        assertThat(board.getStonesForGivenPitIndex(9)).isEqualTo(2);

        board.setStonesForGivenPitIndex(10, 3);
        assertThat(board.getStonesForGivenPitIndex(10)).isEqualTo(3);

        board.setStonesForGivenPitIndex(11, 4);
        assertThat(board.getStonesForGivenPitIndex(11)).isEqualTo(4);

        board.setStonesForGivenPitIndex(12, 5);
        assertThat(board.getStonesForGivenPitIndex(12)).isEqualTo(5);

        board.setStonesForGivenPitIndex(13, 4);
        assertThat(board.getStonesForGivenPitIndex(13)).isEqualTo(4);
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenPitIndexIsOutOfRange() {
        try {
            board.setStonesForGivenPitIndex(-1, 0);
            fail("Pit index shouldn't be less than 0!");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Pit index must be between 0 and 13 inclusive!");
        }

        try {
            board.setStonesForGivenPitIndex(14, 0);
            fail("Pit index shouldn't be greater than 13!");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Pit index must be between 0 and 13 inclusive!");
        }
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenStonesAreLessThanZero() {
        try {
            board.setStonesForGivenPitIndex(5, -1);
            fail("Stones shouldn't be less than 0!");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Stones cannot be less than 0!");
        }
    }

    @Test
    void shouldVerifyIfPlayerPitsAreEmptyOrNot() {
        assertThat(board.isPlayerOnePitsEmpty()).isFalse();
        assertThat(board.isPlayerTwoPitsEmpty()).isFalse();

        board.setStonesForGivenPitIndex(0, 0);
        assertThat(board.isPlayerOnePitsEmpty()).isFalse();

        board.setStonesForGivenPitIndex(1, 0);
        assertThat(board.isPlayerOnePitsEmpty()).isFalse();

        board.setStonesForGivenPitIndex(2, 0);
        assertThat(board.isPlayerOnePitsEmpty()).isFalse();

        board.setStonesForGivenPitIndex(3, 0);
        assertThat(board.isPlayerOnePitsEmpty()).isFalse();

        board.setStonesForGivenPitIndex(4, 0);
        assertThat(board.isPlayerOnePitsEmpty()).isFalse();

        board.setStonesForGivenPitIndex(5, 0);
        assertThat(board.isPlayerOnePitsEmpty()).isTrue();

        board.setStonesForGivenPitIndex(6, 32);

        assertThat(board.isPlayerOnePitsEmpty()).isTrue();

        board.setStonesForGivenPitIndex(5, 1);
        assertThat(board.isPlayerOnePitsEmpty()).isFalse();

        assertThat(board.isPlayerTwoPitsEmpty()).isFalse();
    }

    @Test
    void shouldMoveEveryStoneToRelatedHomePit() {
        assertThat(board.getPits()[6].getStones()).isEqualTo(0);

        board.moveAllPlayerOneStonesToHomePit();
        board.moveAllPlayerTwoStonesToHomePit();

        Pit[] pits = board.getPits();

        for (int i = 0; i < pits.length; i++) {
            if (i == 6)
                assertThat(board.getStonesForGivenPitIndex(i)).isEqualTo(36);
            else if (i == 13)
                assertThat(board.getStonesForGivenPitIndex(i)).isEqualTo(36);
            else
                assertThat(board.getStonesForGivenPitIndex(i)).isEqualTo(0);
        }
    }
}