package io.github.okanmenevseoglu.mancalagameapi.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * A unit test class that contains the tests of the {@link Player} class.
 */
class PlayerTest {

    @Test
    void shouldCreatePlayerOrNot() {
        Player player = new Player(PlayerName.ONE);

        assertThat(player.getPlayerName()).isEqualTo(PlayerName.ONE);

        try {
            new Player(null);
        } catch (NullPointerException e) {
            assertThat(e.getMessage()).isEqualTo("Player name cannot be null!");
        }
    }

    @Test
    void shouldGetNextPitIndexForPlayerOne() {
        Player player = new Player(PlayerName.ONE);

        try {
            player.getNextPitIndex(-1);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Incorrect pit number! Pit number should be between 0 and 13.");
        }

        assertThat(player.getNextPitIndex(0)).isEqualTo(1);
        assertThat(player.getNextPitIndex(1)).isEqualTo(2);
        assertThat(player.getNextPitIndex(2)).isEqualTo(3);
        assertThat(player.getNextPitIndex(3)).isEqualTo(4);
        assertThat(player.getNextPitIndex(4)).isEqualTo(5);
        assertThat(player.getNextPitIndex(5)).isEqualTo(6);
        assertThat(player.getNextPitIndex(6)).isEqualTo(7);
        assertThat(player.getNextPitIndex(7)).isEqualTo(8);
        assertThat(player.getNextPitIndex(8)).isEqualTo(9);
        assertThat(player.getNextPitIndex(9)).isEqualTo(10);
        assertThat(player.getNextPitIndex(10)).isEqualTo(11);
        assertThat(player.getNextPitIndex(11)).isEqualTo(12);
        assertThat(player.getNextPitIndex(12)).isEqualTo(0);

        try {
            player.getNextPitIndex(13);
            fail("");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Incorrect pit number! This pit is the home pit of player TWO.");
        }

        try {
            player.getNextPitIndex(14);
            fail("");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Incorrect pit number! Pit number should be between 0 and 13.");
        }
    }

    @Test
    void shouldGetNextPitIndexForPlayerTwo() {
        Player player = new Player(PlayerName.TWO);

        try {
            player.getNextPitIndex(-1);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Incorrect pit number! Pit number should be between 0 and 13.");
        }

        assertThat(player.getNextPitIndex(0)).isEqualTo(1);
        assertThat(player.getNextPitIndex(1)).isEqualTo(2);
        assertThat(player.getNextPitIndex(2)).isEqualTo(3);
        assertThat(player.getNextPitIndex(3)).isEqualTo(4);
        assertThat(player.getNextPitIndex(4)).isEqualTo(5);
        assertThat(player.getNextPitIndex(5)).isEqualTo(7);

        try {
            player.getNextPitIndex(6);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Incorrect pit number! This pit is the home pit of player ONE.");
        }

        assertThat(player.getNextPitIndex(7)).isEqualTo(8);
        assertThat(player.getNextPitIndex(8)).isEqualTo(9);
        assertThat(player.getNextPitIndex(9)).isEqualTo(10);
        assertThat(player.getNextPitIndex(10)).isEqualTo(11);
        assertThat(player.getNextPitIndex(11)).isEqualTo(12);
        assertThat(player.getNextPitIndex(12)).isEqualTo(13);
        assertThat(player.getNextPitIndex(13)).isEqualTo(0);

        try {
            player.getNextPitIndex(14);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Incorrect pit number! Pit number should be between 0 and 13.");
        }
    }

    @Test
    void shouldReturnOppositePitIndexForPlayers() {
        try {
            Player.getOppositePitIndex(-1);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Incorrect pit number! Pit number should be between 0 and 13.");
        }

        assertThat(Player.getOppositePitIndex(0)).isEqualTo(12);
        assertThat(Player.getOppositePitIndex(1)).isEqualTo(11);
        assertThat(Player.getOppositePitIndex(2)).isEqualTo(10);
        assertThat(Player.getOppositePitIndex(3)).isEqualTo(9);
        assertThat(Player.getOppositePitIndex(4)).isEqualTo(8);
        assertThat(Player.getOppositePitIndex(5)).isEqualTo(7);

        try {
            Player.getOppositePitIndex(6);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Incorrect pit number! This pit is the home pit of player ONE.");
        }

        assertThat(Player.getOppositePitIndex(7)).isEqualTo(5);
        assertThat(Player.getOppositePitIndex(8)).isEqualTo(4);
        assertThat(Player.getOppositePitIndex(9)).isEqualTo(3);
        assertThat(Player.getOppositePitIndex(10)).isEqualTo(2);
        assertThat(Player.getOppositePitIndex(11)).isEqualTo(1);
        assertThat(Player.getOppositePitIndex(12)).isEqualTo(0);

        try {
            Player.getOppositePitIndex(13);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Incorrect pit number! This pit is the home pit of player TWO.");
        }

        try {
            Player.getOppositePitIndex(14);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Incorrect pit number! Pit number should be between 0 and 13.");
        }
    }
}
