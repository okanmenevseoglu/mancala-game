package io.github.okanmenevseoglu.mancalagameapi.service.rules.validation;

import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import io.github.okanmenevseoglu.mancalagameapi.service.rules.MancalaGameRule;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * This class is an implementation of {@link MancalaGameRule} interface.
 */
@Component
public class CurrentPlayerNonEmptyPitValidationRule implements MancalaGameRule {

    /**
     * This method validates if the given index belongs to current player and is not empty.
     *
     * @param board    a board
     * @param pitIndex an integer pit index
     * @return a boolean value
     */
    @Override
    public boolean isValidated(Board board, Integer pitIndex) {
        return Arrays.asList(board.getCurrentPlayer().getPlayerPitIndexes()).contains(pitIndex) && board.getStonesForGivenPitIndex(pitIndex) != 0;
    }

    /**
     * This method returns the turn information message for given player.
     *
     * @param board a board
     * @return a string message
     */
    @Override
    public String getMessage(Board board) {
        return "It is Player " + board.getCurrentPlayer().getPlayerName() + "'s turn.";
    }
}
