package io.github.okanmenevseoglu.mancalagameapi.service.rules.validation;

import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import io.github.okanmenevseoglu.mancalagameapi.model.Player;
import io.github.okanmenevseoglu.mancalagameapi.service.rules.MancalaGameRule;
import org.springframework.stereotype.Component;

/**
 * This class is an implementation of {@link MancalaGameRule} interface.
 */
@Component
public class GameOverValidationRule implements MancalaGameRule {

    /**
     * This method validates if the game is not over.
     *
     * @param board    a board
     * @param pitIndex an integer pit index
     * @return a boolean value
     */
    @Override
    public boolean isValidated(Board board, Integer pitIndex) {
        return board.getPits()[Player.PLAYER_ONE_HOME_PIT_INDEX].getStones() + board.getPits()[Player.PLAYER_TWO_HOME_PIT_INDEX].getStones() != Board.TOTAL_STONES;
    }

    /**
     * This method returns a game over message.
     *
     * @param board a board
     * @return a string message
     */
    @Override
    public String getMessage(Board board) {
        return "GAME OVER!";
    }
}
