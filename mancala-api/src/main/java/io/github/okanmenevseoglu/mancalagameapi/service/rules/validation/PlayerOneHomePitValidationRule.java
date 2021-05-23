package io.github.okanmenevseoglu.mancalagameapi.service.rules.validation;

import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import io.github.okanmenevseoglu.mancalagameapi.model.Player;
import io.github.okanmenevseoglu.mancalagameapi.service.rules.MancalaGameRule;
import org.springframework.stereotype.Component;

/**
 * This class is an implementation of {@link MancalaGameRule} interface.
 */
@Component
public class PlayerOneHomePitValidationRule implements MancalaGameRule {

    /**
     * This method validates if the given index doesn't belong to the home pit of player one.
     *
     * @param board    a board
     * @param pitIndex an integer pit index
     * @return a boolean value
     */
    @Override
    public boolean isValidated(Board board, Integer pitIndex) {
        return !Player.isPlayerOneHomePitIndex(pitIndex);
    }

    /**
     * This method returns a player one home pit message.
     *
     * @param board a board
     * @return a string message
     */
    @Override
    public String getMessage(Board board) {
        return "Invalid move! This pit is the home pit of Player ONE!";
    }
}
