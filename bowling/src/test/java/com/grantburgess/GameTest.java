package com.grantburgess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {
    private Game game;

    private void rollMany(int rolls, int pins) {
        for (int i = 0; i < rolls; i++)
            game.roll(pins);
    }

    private void assertScore(int expected) {
        assertEquals(expected, game.score());
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    private void rollStrike() {
        game.roll(10);
    }

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void gutter_game() {
        rollMany(20, 0);

        assertScore(0);
    }

    @Test
    public void all_ones() {
        rollMany(20, 1);

        assertScore(20);
    }

    @Test
    public void one_spare() {
        rollSpare();
        game.roll(3);
        game.roll(3);
        rollMany(16, 0);

        assertScore(19);
    }

    @Test
    public void one_strike() {
        rollStrike();
        game.roll(6);
        game.roll(3);
        rollMany(16, 0);

        assertScore(28);
    }

    @Test
    public void perfect_score() {
        rollMany(12, 10);

        assertScore(300);
    }
}
