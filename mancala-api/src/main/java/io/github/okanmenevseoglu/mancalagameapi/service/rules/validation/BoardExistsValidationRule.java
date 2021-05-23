package io.github.okanmenevseoglu.mancalagameapi.service.rules.validation;

import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import io.github.okanmenevseoglu.mancalagameapi.service.rules.MancalaGameRule;
import org.springframework.stereotype.Component;

/**
 * This class is an implementation of {@link MancalaGameRule} interface.
 */
@Component
public class BoardExistsValidationRule implements MancalaGameRule {

    /**
     * This method validates if the board exists.
     *
     * @param board    a board
     * @param pitIndex an integer pit index
     * @return a boolean value
     */
    @Override
    public boolean isValidated(Board board, Integer pitIndex) {
        return board != null;
    }

    /**
     * This method returns board not found message.
     *
     * @param board a board
     * @return a string message
     */
    @Override
    public String getMessage(Board board) {
        return "The board is not found. Please initialize a new board!";
    }
}
