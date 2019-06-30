package com.grantburgess.tennis;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GameTest {
    private Game game;

    private void assertScore(String... expected) {
        assertEquals(Arrays.asList(expected), game.getScore());
    }

    private void scorePlayerOne(int times) {
        for (int i = 0; i < times; i++)
            game.player1Scored();
    }

    private void scorePlayerTwo(int times) {
        for (int i = 0; i < times; i++)
            game.player2Scored();
    }

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void love_all_game() {
        assertScore("0", "0");
    }

    @Test
    public void fifteen_love_game() {
        game.player1Scored();
        assertScore("15", "0");
    }

    @Test
    public void fifteen_all_game() {
        game.player1Scored();
        game.player2Scored();
        assertScore("15", "15");
    }

    @Test
    public void thirty_love_game() {
        scorePlayerOne(2);
        assertScore("30", "0");
    }

    @Test
    public void love_forty_game() {
        scorePlayerTwo(3);
        assertScore("0", "40");
    }

    @Test
    public void deuce_game() {
        scorePlayerOne(3);
        scorePlayerTwo(3);
        assertScore("deuce");
    }

    @Test
    public void more_than_one_deuce_game() {
        scorePlayerOne(4);
        scorePlayerTwo(4);
        assertScore("deuce");
    }

    @Test
    public void advantage_player_one_game() {
        scorePlayerOne(4);
        scorePlayerTwo(3);
        assertScore("Advantage Player One");
    }

    @Test
    public void more_than_one_advantage_game() {
        scorePlayerOne(5);
        scorePlayerTwo(4);
        assertScore("Advantage Player One");
    }

    @Test
    public void player_one_wins_forty_love() {
        scorePlayerOne(4);
        assertScore("Player 1 wins game");
    }

    @Test
    public void player_two_wins_forty_love() {
        scorePlayerTwo(4);
        assertScore("Player 2 wins game");
    }

    @Test
    public void player_one_wins_advantage_game() {
        scorePlayerOne(6);
        scorePlayerTwo(4);
        assertScore("Player 1 wins game");
    }
}
