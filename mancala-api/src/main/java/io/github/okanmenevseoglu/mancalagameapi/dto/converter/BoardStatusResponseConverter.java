package io.github.okanmenevseoglu.mancalagameapi.dto.converter;

import io.github.okanmenevseoglu.mancalagameapi.dto.response.BoardStatusResponse;
import io.github.okanmenevseoglu.mancalagameapi.dto.response.PitResponse;
import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import io.github.okanmenevseoglu.mancalagameapi.model.Player;
import org.springframework.stereotype.Component;

/**
 * This is a converter that converts a {@link Board} to a {@link BoardStatusResponse} DTO.
 */
@Component
public class BoardStatusResponseConverter implements BaseDTOConverter<Board, BoardStatusResponse> {

    private final PitResponseConverter pitResponseConverter;

    public BoardStatusResponseConverter(PitResponseConverter pitResponseConverter) {
        this.pitResponseConverter = pitResponseConverter;
    }

    /**
     * This method overrides the functional apply method to be used in a singular conversion.
     *
     * @param board a board object to convert
     * @return a board status response object to be converted to
     */
    @Override
    public BoardStatusResponse apply(Board board) {
        PitResponse[] pits = null;
        String currentPlayerName = null;
        String message = null;

        if (board == null)
            message = getBoardNotFoundMessage();
        else {
            pits = pitResponseConverter.convertFromArrayToArray(board.getPits());

            Player currentPlayer = board.getCurrentPlayer();

            if (currentPlayer != null) {
                currentPlayerName = board.getCurrentPlayer().getPlayerName().name();
                message = "It is Player " + board.getCurrentPlayer().getPlayerName().name() + "'s turn!";
            }
        }

        return BoardStatusResponse.builder()
                .pits(pits)
                .currentPlayer(currentPlayerName)
                .infoMessage(message)
                .build();
    }

    /**
     * This method is similar to the apply with an extra message field.
     *
     * @param board   a board object to convert
     * @param message a string message to add to the response
     * @return a board status response object to be converted to
     */
    public BoardStatusResponse convert(Board board, String message) {
        BoardStatusResponse boardStatusResponse = convert(board);

        if (board != null)
            boardStatusResponse.setInfoMessage(message);

        return boardStatusResponse;
    }

    public String getBoardNotFoundMessage() {
        return "The board is not found. Please initialize a new board!";
    }
}
