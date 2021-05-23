package io.github.okanmenevseoglu.mancalagameapi.model;

import lombok.Getter;

/**
 * This class is the representation of a player.
 */
@Getter
public class Player {

    /**
     * A constant array holding the pit indexes of player one.
     */
    public static final Integer[] PLAYER_ONE_PIT_INDEXES = {0, 1, 2, 3, 4, 5, 6};

    /**
     * A constant holding the home pit index of player one.
     */
    public static final Integer PLAYER_ONE_HOME_PIT_INDEX = 6;

    /**
     * A constant array holding the map of next pit indexes of player one.
     */
    public static final Integer[] PLAYER_ONE_NEXT_PIT_INDEXES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0, null};

    /**
     * A constant array holding the pit indexes of player two.
     */
    public static final Integer[] PLAYER_TWO_PIT_INDEXES = {7, 8, 9, 10, 11, 12, 13};

    /**
     * A constant holding the home pit index of player two.
     */
    public static final Integer PLAYER_TWO_HOME_PIT_INDEX = 13;

    /**
     * A constant array holding the map of next pit indexes of player two.
     */
    public static final Integer[] PLAYER_TWO_NEXT_PIT_INDEXES = {1, 2, 3, 4, 5, 7, null, 8, 9, 10, 11, 12, 13, 0};

    /**
     * A constant array holding the map of opposite pit indexes.
     */
    public static final Integer[] OPPOSITE_PIT_INDEXES = {12, 11, 10, 9, 8, 7, null, 5, 4, 3, 2, 1, 0, null};

    /**
     * A final enum to hold the name of a player.
     */
    private final PlayerName playerName;

    /**
     * Constructor to set player name.
     *
     * @param playerName a string player name
     * @throws NullPointerException if the player name is null
     */
    public Player(PlayerName playerName) {
        if (playerName == null)
            throw new NullPointerException("Player name cannot be null!");

        this.playerName = playerName;
    }

    /**
     * Gets the opposite index of a given pit index from the array map.
     *
     * @param pitIndex an integer pit index
     * @return an integer pit index
     * @throws IllegalStateException if pit indexes are not valid
     */
    public static int getOppositePitIndex(Integer pitIndex) {
        validateIndexForOppositePits(pitIndex);

        return Player.OPPOSITE_PIT_INDEXES[pitIndex];
    }

    private static void validateIndexForOppositePits(Integer pitIndex) {
        validateIfPitIndexBetweenLimits(pitIndex);
        validateIfPitIndexIsNotTheGivenHomePit(pitIndex, Player.PLAYER_ONE_HOME_PIT_INDEX, PlayerName.ONE);
        validateIfPitIndexIsNotTheGivenHomePit(pitIndex, Player.PLAYER_TWO_HOME_PIT_INDEX, PlayerName.TWO);
    }

    private static void validateIfPitIndexBetweenLimits(Integer pitIndex) {
        if (pitIndex < PLAYER_ONE_PIT_INDEXES[0] || pitIndex > PLAYER_TWO_HOME_PIT_INDEX)
            throw new IllegalStateException("Incorrect pit number! Pit number should be between " + Player.PLAYER_ONE_PIT_INDEXES[0] + " and " + Player.PLAYER_TWO_HOME_PIT_INDEX + ".");
    }

    private static void validateIfPitIndexIsNotTheGivenHomePit(Integer pitIndex, Integer homePitIndex, PlayerName playerName) {
        if (pitIndex.equals(homePitIndex))
            throw new IllegalStateException("Incorrect pit number! This pit is the home pit of player " + playerName.name() + ".");
    }

    /**
     * Checks if the given index is the player one home pit.
     *
     * @param pitIndex an integer pit index
     * @return a boolean
     */
    public static boolean isPlayerOneHomePitIndex(Integer pitIndex) {
        return pitIndex.equals(PLAYER_ONE_HOME_PIT_INDEX);
    }

    /**
     * Checks if the given index is the player two home pit.
     *
     * @param pitIndex an integer pit index
     * @return a boolean
     */
    public static boolean isPlayerTwoHomePitIndex(Integer pitIndex) {
        return pitIndex.equals(PLAYER_TWO_HOME_PIT_INDEX);
    }

    /**
     * Checks if the player name is one.
     *
     * @return boolean value
     */
    public boolean isPlayerOne() {
        return this.playerName == PlayerName.ONE;
    }

    /**
     * Gets the next index for a given pit index from the array map.
     *
     * @param pitIndex an integer pit index
     * @return an integer pit index
     * @throws IllegalStateException if pit indexes are not valid
     */
    public int getNextPitIndex(Integer pitIndex) {
        validateIfPitIndexBetweenLimits(pitIndex);

        if (isPlayerOne()) {
            validateIfPitIndexIsNotTheGivenHomePit(pitIndex, Player.PLAYER_TWO_HOME_PIT_INDEX, PlayerName.TWO);
            return Player.PLAYER_ONE_NEXT_PIT_INDEXES[pitIndex];
        } else {
            validateIfPitIndexIsNotTheGivenHomePit(pitIndex, Player.PLAYER_ONE_HOME_PIT_INDEX, PlayerName.ONE);
            return Player.PLAYER_TWO_NEXT_PIT_INDEXES[pitIndex];
        }
    }

    /**
     * Gets player pit indexes based on the player name.
     *
     * @return an integer array player pit indexes
     */
    public Integer[] getPlayerPitIndexes() {
        return isPlayerOne() ? Player.PLAYER_ONE_PIT_INDEXES : Player.PLAYER_TWO_PIT_INDEXES;
    }

    /**
     * Gets the player home pit indexe based on the player name.
     *
     * @return an array player home index
     */
    public Integer getHomePitIndex() {
        return isPlayerOne() ? Player.PLAYER_ONE_HOME_PIT_INDEX : Player.PLAYER_TWO_HOME_PIT_INDEX;
    }
}
