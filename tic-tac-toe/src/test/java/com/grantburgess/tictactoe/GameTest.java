package com.grantburgess.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static com.grantburgess.tictactoe.Game.Player.CROSS;
import static com.grantburgess.tictactoe.Game.Player.NAUGHT;
import static org.junit.Assert.assertEquals;

public class GameTest {
    private Game game;

    private void assertGameStatus(Game.Status expectedStatus) {
        assertEquals(expectedStatus, game.status());
    }

    private void makeMoveFor(Game.Player player, int x, int y) {
        if (CROSS.equals(player))
            game.crossMoves(new Game.Board.Coordinate(x, y));
        else
            game.naughtMoves(new Game.Board.Coordinate(x, y));
    }

    private void playDraw() {
        makeMoveFor(CROSS, 0, 0);
        makeMoveFor(NAUGHT, 0, 1);
        makeMoveFor(CROSS, 0, 2);
        makeMoveFor(NAUGHT, 1, 2);
        makeMoveFor(CROSS, 1, 1);
        makeMoveFor(NAUGHT, 2, 0);
        makeMoveFor(CROSS, 1, 0);
        makeMoveFor(NAUGHT, 2, 2);
        makeMoveFor(CROSS, 2, 1);
    }

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void upon_creation_game_not_started() {
        assertGameStatus(Game.Status.NOT_STARTED);
    }

    @Test
    public void after_one_move_game_is_in_progress() {
        makeMoveFor(CROSS, 0, 0);

        assertGameStatus(Game.Status.IN_PROGRESS);
    }

    @Test(expected = Game.MoveOutOfOrder.class)
    public void cross_must_move_first() {
        makeMoveFor(NAUGHT, 0, 0);
    }

    @Test(expected = Game.MoveOutOfOrder.class)
    public void cannot_play_twice_in_a_row() {
        makeMoveFor(CROSS, 0, 0);
        makeMoveFor(CROSS, 0, 1);
    }

    @Test(expected = Game.Board.CellTaken.class)
    public void cannot_play_same_cell() {
        makeMoveFor(CROSS, 0, 0);
        makeMoveFor(NAUGHT, 0, 0);
    }

    @Test(expected = Game.Board.MoveOutOfBounds.class)
    public void coordinates_cannot_be_outside_board() {
        makeMoveFor(CROSS, 3, 0);
    }

    @Test(expected = Game.Board.MoveOutOfBounds.class)
    public void coordinates_cannot_be_negative() {
        makeMoveFor(CROSS, 0, -1);
    }

    @Test
    public void play_draw() {
        // X O X
        // X X O
        // O X O
        playDraw();

        assertGameStatus(Game.Status.DRAW);
    }

    @Test(expected = Game.GameOver.class)
    public void cannot_play_another_move_after_draw() {
        // X O X
        // X X O
        // O X O
        playDraw();
        makeMoveFor(NAUGHT, 2, 2);
    }

    @Test(expected = Game.GameOver.class)
    public void cannot_play_after_win() {
        // X   O
        // O O
        // X X X
        makeMoveFor(CROSS, 0, 0);
        makeMoveFor(NAUGHT, 0, 2);
        makeMoveFor(CROSS, 2, 0);
        makeMoveFor(NAUGHT, 1, 0);
        makeMoveFor(CROSS, 2, 2);
        makeMoveFor(NAUGHT, 1, 1);
        makeMoveFor(CROSS, 2, 1);
        //attempt
        makeMoveFor(NAUGHT, 0, 1);
    }

    @Test
    public void winning_moves() {
        // X   O
        // X O
        // X
        makeMoveFor(CROSS, 0, 0);
        makeMoveFor(NAUGHT, 0, 2);
        makeMoveFor(CROSS, 2, 0);
        makeMoveFor(NAUGHT, 1, 1);
        makeMoveFor(CROSS, 1, 0);

        assertGameStatus(Game.Status.CROSS_WINS);

        //   X O
        // O X
        //   X
        game = new Game();
        makeMoveFor(CROSS, 0, 1);
        makeMoveFor(NAUGHT, 0, 2);
        makeMoveFor(CROSS, 2, 1);
        makeMoveFor(NAUGHT, 1, 0);
        makeMoveFor(CROSS, 1, 1);

        assertGameStatus(Game.Status.CROSS_WINS);

        //   O X
        // O   X
        //     X
        game = new Game();
        makeMoveFor(CROSS, 0, 2);
        makeMoveFor(NAUGHT, 0, 1);
        makeMoveFor(CROSS, 2, 2);
        makeMoveFor(NAUGHT, 1, 0);
        makeMoveFor(CROSS, 1, 2);

        assertGameStatus(Game.Status.CROSS_WINS);

        // O O O
        // X X
        //   X
        game = new Game();
        makeMoveFor(CROSS, 1, 0);
        makeMoveFor(NAUGHT, 0, 0);
        makeMoveFor(CROSS, 1, 1);
        makeMoveFor(NAUGHT, 0, 2);
        makeMoveFor(CROSS, 2, 1);
        makeMoveFor(NAUGHT, 0, 1);

        assertGameStatus(Game.Status.NAUGHT_WINS);

        // X X
        // O O O
        //   X
        game = new Game();
        makeMoveFor(CROSS, 0, 0);
        makeMoveFor(NAUGHT, 1, 0);
        makeMoveFor(CROSS, 0, 1);
        makeMoveFor(NAUGHT, 1, 2);
        makeMoveFor(CROSS, 2, 1);
        makeMoveFor(NAUGHT, 1, 1);

        assertGameStatus(Game.Status.NAUGHT_WINS);

        // X X
        //   X
        // O O O
        game = new Game();
        makeMoveFor(CROSS, 0, 0);
        makeMoveFor(NAUGHT, 2, 0);
        makeMoveFor(CROSS, 0, 1);
        makeMoveFor(NAUGHT, 2, 2);
        makeMoveFor(CROSS, 1, 1);
        makeMoveFor(NAUGHT, 2, 1);

        assertGameStatus(Game.Status.NAUGHT_WINS);

        // X   O
        // O X
        //     X
        game = new Game();
        makeMoveFor(CROSS, 0, 0);
        makeMoveFor(NAUGHT, 0, 2);
        makeMoveFor(CROSS, 1, 1);
        makeMoveFor(NAUGHT, 1, 0);
        makeMoveFor(CROSS, 2, 2);

        assertGameStatus(Game.Status.CROSS_WINS);

        // X   O
        // X O
        // O   X
        game = new Game();
        makeMoveFor(CROSS, 0, 0);
        makeMoveFor(NAUGHT, 0, 2);
        makeMoveFor(CROSS, 2, 2);
        makeMoveFor(NAUGHT, 1, 1);
        makeMoveFor(CROSS, 1, 0);
        makeMoveFor(NAUGHT, 2, 0);

        assertGameStatus(Game.Status.NAUGHT_WINS);
    }
}
