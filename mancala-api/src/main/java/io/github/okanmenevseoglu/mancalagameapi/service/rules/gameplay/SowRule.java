package io.github.okanmenevseoglu.mancalagameapi.service.rules.gameplay;

import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import io.github.okanmenevseoglu.mancalagameapi.model.Player;
import io.github.okanmenevseoglu.mancalagameapi.service.rules.MancalaGameRule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class is an implementation of {@link MancalaGameRule} interface.
 */
@Component
public class SowRule implements MancalaGameRule {

    /**
     * This is an implementation of the process method to get a message if the rule is valid.
     *
     * @param board    a board
     * @param pitIndex an integer pit index
     * @return a string info message
     */
    @Override
    public String process(Board board, Integer pitIndex) {
        return sow(board, pitIndex);
    }

    /**
     * This method validates is the entry point
     *
     * @param board    a board
     * @param pitIndex an integer pit index
     * @return a boolean value
     */
    public String sow(Board board, Integer pitIndex) {
        Integer stones = board.getStonesForGivenPitIndex(pitIndex);
        board.setStonesForGivenPitIndex(pitIndex, 0);

        boolean playAgain = moveStones(board, pitIndex, stones);

        return getInfoMessage(board, playAgain);
    }

    private boolean moveStones(Board board, Integer pitIndex, Integer stones) {
        boolean playAgain = false;

        Player currentPlayer = board.getCurrentPlayer();

        for (int i = stones; i > 0; i--) {
            if (isValidCollectAllMove(board, pitIndex, currentPlayer, i))
                collectAllOppositeStonesToHomePit(board, pitIndex, currentPlayer);
            else {
                if (i == 1 && isNextMoveInHomePit(pitIndex, currentPlayer)) {
                    playAgain = true;
                }

                pitIndex = currentPlayer.getNextPitIndex(pitIndex);

                increaseStonesByOne(board, pitIndex);
            }
        }

        return playAgain;
    }

    private void increaseStonesByOne(Board board, Integer pitIndex) {
        board.setStonesForGivenPitIndex(pitIndex, board.getStonesForGivenPitIndex(pitIndex) + 1);
    }

    private boolean isValidCollectAllMove(Board board, Integer pitIndex, Player currentPlayer, int i) {
        return i == 1 && isNextMoveInAnEmptyPit(board, pitIndex, currentPlayer) && isNextMoveInAnOwnPit(pitIndex, currentPlayer) && isOppositeOfNextPitNotEmpty(board, pitIndex, currentPlayer);
    }

    private void collectAllOppositeStonesToHomePit(Board board, Integer pitIndex, Player currentPlayer) {
        int nextPitIndex = currentPlayer.getNextPitIndex(pitIndex);
        int oppositePitIndex = Player.getOppositePitIndex(nextPitIndex);
        int oppositeOfNextPitStones = board.getStonesForGivenPitIndex(oppositePitIndex);
        int ownHomePitStones = board.getStonesForGivenPitIndex(currentPlayer.getHomePitIndex());

        board.setStonesForGivenPitIndex(oppositePitIndex, 0);
        board.setStonesForGivenPitIndex(currentPlayer.getHomePitIndex(), ownHomePitStones + oppositeOfNextPitStones + 1);
    }

    private String getInfoMessage(Board board, boolean playAgain) {
        String message;

        if (board.moveAllOpponentStonesToTheirHomePitIfAllAPlayerPitsAreEmpty()) {
            message = getWinMessage(board);
            board.setCurrentPlayer(null);
        } else {
            board.setCurrentPlayer(board.getNextPlayer(playAgain));
            message = "Player " + board.getCurrentPlayer().getPlayerName() + " should select a pit to move.";
        }

        return message;
    }

    /**
     * Checks if the next move is in the home pit.
     *
     * @param pitIndex an integer pit index
     * @param player   a player
     * @return a boolean
     */
    boolean isNextMoveInHomePit(Integer pitIndex, Player player) {
        return player.getNextPitIndex(pitIndex) == player.getHomePitIndex();
    }

    /**
     * Checks if the next move is in an own empty pit excluding home pits.
     *
     * @param pitIndex an integer pit index
     * @param player   a player
     * @return a boolean
     */
    private boolean isNextMoveInAnOwnPit(Integer pitIndex, Player player) {
        int nextPitIndex = player.getNextPitIndex(pitIndex);

        return Arrays.stream(player.getPlayerPitIndexes())
                .filter(i -> !i.equals(player.getHomePitIndex()))
                .collect(Collectors.toList())
                .contains(nextPitIndex);
    }

    private boolean isNextMoveInAnEmptyPit(Board board, Integer pitIndex, Player player) {
        int nextPitIndex = player.getNextPitIndex(pitIndex);

        return board.getStonesForGivenPitIndex(nextPitIndex) == 0;
    }

    private boolean isOppositeOfNextPitNotEmpty(Board board, Integer pitIndex, Player player) {
        int nextPitIndex = player.getNextPitIndex(pitIndex);

        int oppositePitIndex = Player.getOppositePitIndex(nextPitIndex);

        return board.getStonesForGivenPitIndex(oppositePitIndex) != 0;
    }

    /**
     * Returns a win message based on the home pit stones count of the players.
     *
     * @param board a board
     * @return a string message
     */
    String getWinMessage(Board board) {
        Integer playerOneHomePitStones = board.getStonesForGivenPitIndex(Player.PLAYER_ONE_HOME_PIT_INDEX);
        Integer playerTwoHomePitStones = board.getStonesForGivenPitIndex(Player.PLAYER_TWO_HOME_PIT_INDEX);

        StringBuilder message = new StringBuilder("GAME OVER!")
                .append(" ");

        if (playerOneHomePitStones > playerTwoHomePitStones)
            message.append("Player ONE has WON!");
        else if (playerTwoHomePitStones > playerOneHomePitStones)
            message.append("Player TWO has WON!");
        else
            message.append("The game has ended in a DRAW!");

        return message.toString();
    }
}
