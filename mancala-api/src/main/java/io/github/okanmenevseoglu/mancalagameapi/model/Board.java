package io.github.okanmenevseoglu.mancalagameapi.model;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is the representation of a board. A board has the following index structure:
 * <p>
 * // *  ------------------------Player 2 Pits------------------------
 * // *                |   |12 |11 |10 | 9 | 8 | 7 |   |
 * // *  Player 2 Home |13 |-----------------------| 6 | Player 1 Home
 * // *                |   | 0 | 1 | 2 | 3 | 4 | 5 |   |
 * // *  ------------------------Player 1 Pits------------------------
 * </p>
 */
@Getter
@Setter
public class Board {

    /**
     * A constant holding the size of a board.
     */
    public static final int BOARD_SIZE = Player.PLAYER_ONE_PIT_INDEXES.length + Player.PLAYER_TWO_PIT_INDEXES.length;

    /**
     * A constant holding the total stones exist.
     */
    public static final int TOTAL_STONES = (BOARD_SIZE - 2) * Pit.DEFAULT_STONES;

    /**
     * A final variable holding the player one.
     */
    private final Player player1;

    /**
     * A final variable holding the player two.
     */
    private final Player player2;

    /**
     * A final array variable holding the pits.
     */
    private final Pit[] pits;

    /**
     * A variable to hold current player.
     */
    private Player currentPlayer;

    /**
     * Constructor that creates players, sets current player, creates and fills the pits.
     */
    public Board() {
        this.player1 = new Player(PlayerName.ONE);
        this.player2 = new Player(PlayerName.TWO);
        this.currentPlayer = player1;

        this.pits = new Pit[Player.PLAYER_ONE_PIT_INDEXES.length + Player.PLAYER_TWO_PIT_INDEXES.length];
        fillPlayerPits();
    }

    /**
     * Returns the player that will play next.
     *
     * @param playAgain boolean value to play again or not
     * @return a player
     */
    public Player getNextPlayer(boolean playAgain) {
        if (playAgain) {
            return currentPlayer;
        }

        return currentPlayer.isPlayerOne() ? player2 : player1;
    }

    /**
     * Sets stones for a given pit index to given value.
     *
     * @param pitIndex an Integer value
     * @param stones   an Integer value
     */
    public void setStonesForGivenPitIndex(Integer pitIndex, Integer stones) {
        validateIfPitIndexIsInRange(pitIndex);

        pits[pitIndex].setStones(stones);
    }

    /**
     * Gets stones for a given pit index.
     *
     * @param pitIndex an Integer value
     * @return an Integer value
     */
    public Integer getStonesForGivenPitIndex(Integer pitIndex) {
        validateIfPitIndexIsInRange(pitIndex);

        return pits[pitIndex].getStones();
    }

    /**
     * Checks if a game should end.
     */
    public boolean moveAllOpponentStonesToTheirHomePitIfAllAPlayerPitsAreEmpty() {
        if (isPlayerOnePitsEmpty()) {
            moveAllPlayerTwoStonesToHomePit();
            return true;
        } else if (isPlayerTwoPitsEmpty()) {
            moveAllPlayerOneStonesToHomePit();
            return true;
        }
        return false;
    }

    /**
     * Checks if player one pits are empty.
     *
     * @return boolean value
     */
    public boolean isPlayerOnePitsEmpty() {
        return isPlayerPitsEmpty(player1);
    }

    /**
     * Checks if player two pits are empty.
     *
     * @return boolean value
     */
    public boolean isPlayerTwoPitsEmpty() {
        return isPlayerPitsEmpty(player2);
    }

    /**
     * Moves all the stones of player one to the related home pit.
     */
    public void moveAllPlayerOneStonesToHomePit() {
        moveAllStonesToHomePit(player1);
    }

    /**
     * Moves all the stones of player two to the related home pit.
     */
    public void moveAllPlayerTwoStonesToHomePit() {
        moveAllStonesToHomePit(player2);
    }

    private void fillPlayerPits() {
        for (int i = 0; i < this.pits.length; i++) {
            if (i == Player.PLAYER_ONE_HOME_PIT_INDEX || i == Player.PLAYER_TWO_HOME_PIT_INDEX)
                pits[i] = new Pit(0);
            else
                pits[i] = new Pit();
        }
    }

    private void validateIfPitIndexIsInRange(Integer pitIndex) {
        if (pitIndex < 0 || pitIndex > BOARD_SIZE - 1)
            throw new IllegalStateException("Pit index must be between 0 and " + (BOARD_SIZE - 1) + " inclusive!");
    }

    private void moveAllStonesToHomePit(Player player) {
        Integer[] playerIndexes = player.getPlayerPitIndexes();

        for (int i = 0; i < playerIndexes.length - 1; i++) {
            Integer pitIndex = playerIndexes[i];

            Integer stones = getStonesForGivenPitIndex(pitIndex);
            setStonesForGivenPitIndex(pitIndex, 0);

            Integer playerHomePit = player.getHomePitIndex();

            Integer totalStones = getStonesForGivenPitIndex(playerHomePit) + stones;
            setStonesForGivenPitIndex(playerHomePit, totalStones);
        }
    }

    private boolean isPlayerPitsEmpty(Player player) {
        Integer[] playerIndexes = player.getPlayerPitIndexes();

        for (int i = 0; i < playerIndexes.length - 1; i++) {
            if (getStonesForGivenPitIndex(playerIndexes[i]) != 0)
                return false;
        }

        return true;
    }
}
