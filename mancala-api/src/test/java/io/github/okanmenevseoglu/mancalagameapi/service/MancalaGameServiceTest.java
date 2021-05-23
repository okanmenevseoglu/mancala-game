package io.github.okanmenevseoglu.mancalagameapi.service;

import io.github.okanmenevseoglu.mancalagameapi.dto.response.BoardStatusResponse;
import io.github.okanmenevseoglu.mancalagameapi.dto.response.PitResponse;
import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An integration test class that contains the tests of the {@link MancalaGameService} class.
 */
@SpringBootTest
class MancalaGameServiceTest {

    @Autowired
    private MancalaGameService mancalaGameService;

    @BeforeEach
    void shouldResetBoard() {
        mancalaGameService.initializeNewBoard();
    }

    @Test
    void shouldGetBoardStatus() {
        BoardStatusResponse response = mancalaGameService.getBoardStatus();

        PitResponse[] pits = response.getPits();

        // ------------------------Player 2 Pits------------------------
        //               |   | 6 | 6 | 6 | 6 | 6 | 6 |   |
        // Player 2 Home | 0 |-----------------------| 0 | Player 1 Home
        //               |   | 6 | 6 | 6 | 6 | 6 | 6 |   |
        // ------------------------Player 1 Pits------------------------

        assertThat(pits[0].getStones()).isEqualTo(6);
        assertThat(pits[1].getStones()).isEqualTo(6);
        assertThat(pits[2].getStones()).isEqualTo(6);
        assertThat(pits[3].getStones()).isEqualTo(6);
        assertThat(pits[4].getStones()).isEqualTo(6);
        assertThat(pits[5].getStones()).isEqualTo(6);

        assertThat(pits[6].getStones()).isEqualTo(0);

        assertThat(pits[7].getStones()).isEqualTo(6);
        assertThat(pits[8].getStones()).isEqualTo(6);
        assertThat(pits[9].getStones()).isEqualTo(6);
        assertThat(pits[10].getStones()).isEqualTo(6);
        assertThat(pits[11].getStones()).isEqualTo(6);
        assertThat(pits[12].getStones()).isEqualTo(6);

        assertThat(pits[13].getStones()).isEqualTo(0);
    }

    @Test
    void shouldSowWithBoardState() {
        // Player ONE's turn
        BoardStatusResponse response = mancalaGameService.sow(0);

        PitResponse[] pits = response.getPits();

        // ------------------------Player 2 Pits------------------------
        //               |   | 6 | 6 | 6 | 6 | 6 | 6 |   |
        // Player 2 Home | 0 |-----------------------| 1 | Player 1 Home
        //               |   | 0 | 7 | 7 | 7 | 7 | 7 |   |
        // ------------------------Player 1 Pits------------------------

        assertThat(pits[0].getStones()).isEqualTo(0);
        assertThat(pits[1].getStones()).isEqualTo(7);
        assertThat(pits[2].getStones()).isEqualTo(7);
        assertThat(pits[3].getStones()).isEqualTo(7);
        assertThat(pits[4].getStones()).isEqualTo(7);
        assertThat(pits[5].getStones()).isEqualTo(7);

        assertThat(pits[6].getStones()).isEqualTo(1);

        assertThat(pits[7].getStones()).isEqualTo(6);
        assertThat(pits[8].getStones()).isEqualTo(6);
        assertThat(pits[9].getStones()).isEqualTo(6);
        assertThat(pits[10].getStones()).isEqualTo(6);
        assertThat(pits[11].getStones()).isEqualTo(6);
        assertThat(pits[12].getStones()).isEqualTo(6);

        assertThat(pits[13].getStones()).isEqualTo(0);

        // Player ONE's turn again
        response = mancalaGameService.sow(12);

        assertThat(response.getInfoMessage()).isEqualTo("It is Player ONE's turn.");
        assertThat(response.getCurrentPlayer()).isEqualTo("ONE");

        // Player ONE's turn again
        response = mancalaGameService.sow(1);

        pits = response.getPits();

        // ------------------------Player 2 Pits------------------------
        //               |   | 6 | 6 | 6 | 6 | 7 | 7 |   |
        // Player 2 Home | 0 |-----------------------| 2 | Player 1 Home
        //               |   | 0 | 0 | 8 | 8 | 8 | 8 |   |
        // ------------------------Player 1 Pits------------------------

        assertThat(pits[0].getStones()).isEqualTo(0);
        assertThat(pits[1].getStones()).isEqualTo(0);
        assertThat(pits[2].getStones()).isEqualTo(8);
        assertThat(pits[3].getStones()).isEqualTo(8);
        assertThat(pits[4].getStones()).isEqualTo(8);
        assertThat(pits[5].getStones()).isEqualTo(8);

        assertThat(pits[6].getStones()).isEqualTo(2);

        assertThat(pits[7].getStones()).isEqualTo(7);
        assertThat(pits[8].getStones()).isEqualTo(7);
        assertThat(pits[9].getStones()).isEqualTo(6);
        assertThat(pits[10].getStones()).isEqualTo(6);
        assertThat(pits[11].getStones()).isEqualTo(6);
        assertThat(pits[12].getStones()).isEqualTo(6);

        assertThat(pits[13].getStones()).isEqualTo(0);

        // Player TWO's turn
        response = mancalaGameService.sow(7);

        pits = response.getPits();

        // ------------------------Player 2 Pits------------------------
        //               |   | 7 | 7 | 7 | 7 | 8 | 0 |   |
        // Player 2 Home | 1 |-----------------------| 2 | Player 1 Home
        //               |   | 1 | 0 | 8 | 8 | 8 | 8 |   |
        // ------------------------Player 1 Pits------------------------

        assertThat(pits[0].getStones()).isEqualTo(1);
        assertThat(pits[1].getStones()).isEqualTo(0);
        assertThat(pits[2].getStones()).isEqualTo(8);
        assertThat(pits[3].getStones()).isEqualTo(8);
        assertThat(pits[4].getStones()).isEqualTo(8);
        assertThat(pits[5].getStones()).isEqualTo(8);

        assertThat(pits[6].getStones()).isEqualTo(2);

        assertThat(pits[7].getStones()).isEqualTo(0);
        assertThat(pits[8].getStones()).isEqualTo(8);
        assertThat(pits[9].getStones()).isEqualTo(7);
        assertThat(pits[10].getStones()).isEqualTo(7);
        assertThat(pits[11].getStones()).isEqualTo(7);
        assertThat(pits[12].getStones()).isEqualTo(7);

        assertThat(pits[13].getStones()).isEqualTo(1);

        // Player ONE's turn
        response = mancalaGameService.sow(0);

        pits = response.getPits();

        // ------------------------Player 2 Pits------------------------
        //               |   | 7 | 0 | 7 | 7 | 8 | 0 |   |
        // Player 2 Home | 1 |-----------------------| 10 | Player 1 Home
        //               |   | 0 | 0 | 8 | 8 | 8 | 8 |   |
        // ------------------------Player 1 Pits------------------------

        assertThat(pits[0].getStones()).isEqualTo(0);
        assertThat(pits[1].getStones()).isEqualTo(0);
        assertThat(pits[2].getStones()).isEqualTo(8);
        assertThat(pits[3].getStones()).isEqualTo(8);
        assertThat(pits[4].getStones()).isEqualTo(8);
        assertThat(pits[5].getStones()).isEqualTo(8);

        assertThat(pits[6].getStones()).isEqualTo(10);

        assertThat(pits[7].getStones()).isEqualTo(0);
        assertThat(pits[8].getStones()).isEqualTo(8);
        assertThat(pits[9].getStones()).isEqualTo(7);
        assertThat(pits[10].getStones()).isEqualTo(7);
        assertThat(pits[11].getStones()).isEqualTo(0);
        assertThat(pits[12].getStones()).isEqualTo(7);

        assertThat(pits[13].getStones()).isEqualTo(1);

        // Player TWO's turn
        response = mancalaGameService.sow(0);

        assertThat(response.getInfoMessage()).isEqualTo("It is Player TWO's turn.");
        assertThat(response.getCurrentPlayer()).isEqualTo("TWO");
    }

    @Test
    void shouldNotMoveStonesToHomePitIfOppositePlayerHasNoStones() {
        Board board = mancalaGameService.getBoard();

        // Prepare board as the following:
        //
        // ------------------------Player 2 Pits------------------------
        //               |   | 6 | 0 | 6 | 6 | 6 | 6 |   |
        // Player 2 Home | 6 |-----------------------|11 | Player 1 Home
        //               |   | 1 | 0 | 6 | 6 | 6 | 6 |   |
        // ------------------------Player 1 Pits------------------------

        board.setStonesForGivenPitIndex(0, 1);
        board.setStonesForGivenPitIndex(1, 0);
        board.setStonesForGivenPitIndex(6, 11);
        board.setStonesForGivenPitIndex(11, 0);
        board.setStonesForGivenPitIndex(13, 6);

        BoardStatusResponse response = mancalaGameService.sow(0);

        PitResponse[] pits = response.getPits();

        // ------------------------Player 2 Pits------------------------
        //               |   | 6 | 0 | 6 | 6 | 6 | 6 |   |
        // Player 2 Home | 6 |-----------------------|11 | Player 1 Home
        //               |   | 0 | 1 | 6 | 6 | 6 | 6 |   |
        // ------------------------Player 1 Pits------------------------

        assertThat(pits[0].getStones()).isEqualTo(0);
        assertThat(pits[1].getStones()).isEqualTo(1);
        assertThat(pits[2].getStones()).isEqualTo(6);
        assertThat(pits[3].getStones()).isEqualTo(6);
        assertThat(pits[4].getStones()).isEqualTo(6);
        assertThat(pits[5].getStones()).isEqualTo(6);

        assertThat(pits[6].getStones()).isEqualTo(11);

        assertThat(pits[7].getStones()).isEqualTo(6);
        assertThat(pits[8].getStones()).isEqualTo(6);
        assertThat(pits[9].getStones()).isEqualTo(6);
        assertThat(pits[10].getStones()).isEqualTo(6);
        assertThat(pits[11].getStones()).isEqualTo(0);
        assertThat(pits[12].getStones()).isEqualTo(6);

        assertThat(pits[13].getStones()).isEqualTo(6);
    }

    @Test
    void shouldSowOppositeStonesWhenLastPitIsEmpty() {
        Board board = mancalaGameService.getBoard();

        // Prepare board as the following:
        //
        // ------------------------Player 2 Pits------------------------
        //               |   | 4 | 0 | 8 | 0 | 0 | 1 |   |
        // Player 2 Home | 4 |-----------------------|18 | Player 1 Home
        //               |   | 2 | 4 | 0 | 2 | 2 | 0 |   |
        // ------------------------Player 1 Pits------------------------

        board.setStonesForGivenPitIndex(0, 2);
        board.setStonesForGivenPitIndex(1, 4);
        board.setStonesForGivenPitIndex(2, 0);
        board.setStonesForGivenPitIndex(3, 2);
        board.setStonesForGivenPitIndex(4, 2);
        board.setStonesForGivenPitIndex(5, 0);
        board.setStonesForGivenPitIndex(6, 18);
        board.setStonesForGivenPitIndex(7, 1);
        board.setStonesForGivenPitIndex(8, 0);
        board.setStonesForGivenPitIndex(9, 0);
        board.setStonesForGivenPitIndex(10, 8);
        board.setStonesForGivenPitIndex(11, 0);
        board.setStonesForGivenPitIndex(12, 4);
        board.setStonesForGivenPitIndex(13, 4);

        BoardStatusResponse response = mancalaGameService.sow(3);

        PitResponse[] pits = response.getPits();

        // ------------------------Player 2 Pits------------------------
        //               |   | 4 | 0 | 8 | 0 | 0 | 0 |   |
        // Player 2 Home | 4 |-----------------------|20 | Player 1 Home
        //               |   | 2 | 4 | 0 | 0 | 3 | 0 |   |
        // ------------------------Player 1 Pits------------------------

        assertThat(pits[0].getStones()).isEqualTo(2);
        assertThat(pits[1].getStones()).isEqualTo(4);
        assertThat(pits[2].getStones()).isEqualTo(0);
        assertThat(pits[3].getStones()).isEqualTo(0);
        assertThat(pits[4].getStones()).isEqualTo(3);
        assertThat(pits[5].getStones()).isEqualTo(0);

        assertThat(pits[6].getStones()).isEqualTo(20);

        assertThat(pits[7].getStones()).isEqualTo(0);
        assertThat(pits[8].getStones()).isEqualTo(0);
        assertThat(pits[9].getStones()).isEqualTo(0);
        assertThat(pits[10].getStones()).isEqualTo(8);
        assertThat(pits[11].getStones()).isEqualTo(0);
        assertThat(pits[12].getStones()).isEqualTo(4);

        assertThat(pits[13].getStones()).isEqualTo(4);
    }

    @Test
    void shouldNotSowFromHomePits() {
        BoardStatusResponse response = mancalaGameService.sow(6);

        assertThat(response.getInfoMessage()).isEqualTo("Invalid move! This pit is the home pit of Player ONE!");

        response = mancalaGameService.sow(13);

        assertThat(response.getInfoMessage()).isEqualTo("Invalid move! This pit is the home pit of Player TWO!");
    }
}
