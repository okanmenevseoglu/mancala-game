package io.github.okanmenevseoglu.mancalagameapi.service.rules;

import io.github.okanmenevseoglu.mancalagameapi.model.Board;

/**
 * This is an interface that represents a signature of sow rules.
 */
public interface MancalaGameRule {

    /**
     * This is the default implementation of rule processing.
     *
     * @param board a board
     * @return a string message
     */
    default String process(Board board, Integer pitIndex) {
        String message = "";

        if (!isValidated(board, pitIndex))
            return getMessage(board);

        return message;
    }

    /**
     * This is the signature method to check if a rule is valid.
     *
     * @param board    a board
     * @param pitIndex an integer pit index
     * @return a boolean
     */
    default boolean isValidated(Board board, Integer pitIndex) {
        return true;
    }

    /**
     * This is the default implementation of message creation.
     *
     * @param board a board
     * @return a string message
     */
    default String getMessage(Board board) {
        return "";
    }
}
