package io.github.okanmenevseoglu.mancalagameapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This is the response DTO that contains board status to be sent to the client.
 */
@Getter
@Setter
@Builder
public class BoardStatusResponse {

    /**
     * A {@link PitResponse} array that represents the pits on the board.
     */
    private PitResponse[] pits;

    /**
     * An enum that represents the name of the current player who will play.
     */
    private String currentPlayer;

    /**
     * An optional string message that contains information about the current status of the game.
     */
    private String infoMessage;

    /**
     * Constructor for all args.
     *
     * @param pits          a pit response array
     * @param currentPlayer player name of current player
     * @param infoMessage   a string info message
     */
    public BoardStatusResponse(PitResponse[] pits, String currentPlayer, String infoMessage) {
        this.pits = pits;
        this.currentPlayer = currentPlayer;
        this.infoMessage = infoMessage;
    }
}
