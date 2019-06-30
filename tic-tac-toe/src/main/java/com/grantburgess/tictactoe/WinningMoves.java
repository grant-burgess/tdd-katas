package com.grantburgess.tictactoe;

import java.util.Arrays;
import java.util.List;

public class WinningMoves {
    private static final List<List<Board.Coordinate>> winningMoves = Arrays.asList(
            // X _ _
            // X _ _
            // X _ _
            Arrays.asList(
                    new Board.Coordinate(0, 0),
                    new Board.Coordinate(1, 0),
                    new Board.Coordinate(2, 0)
            ),
            // _ X _
            // _ X _
            // _ X _
            Arrays.asList(
                    new Board.Coordinate(0, 1),
                    new Board.Coordinate(1, 1),
                    new Board.Coordinate(2, 1)
            ),
            // _ _ X
            // _ _ X
            // _ _ X
            Arrays.asList(
                    new Board.Coordinate(0, 2),
                    new Board.Coordinate(1, 2),
                    new Board.Coordinate(2, 2)
            ),
            // X X X
            // _ _ _
            // _ _ _
            Arrays.asList(
                    new Board.Coordinate(0, 0),
                    new Board.Coordinate(0, 1),
                    new Board.Coordinate(0, 2)
            ),
            // _ _ _
            // X X X
            // _ _ _
            Arrays.asList(
                    new Board.Coordinate(1, 0),
                    new Board.Coordinate(1, 1),
                    new Board.Coordinate(1, 2)
            ),
            // _ _ _
            // _ _ _
            // X X X
            Arrays.asList(
                    new Board.Coordinate(2, 0),
                    new Board.Coordinate(2, 1),
                    new Board.Coordinate(2, 2)
            ),
            // X _ _
            // _ X _
            // _ _ X
            Arrays.asList(
                    new Board.Coordinate(0, 0),
                    new Board.Coordinate(1, 1),
                    new Board.Coordinate(2, 2)
            ),
            // _ _ X
            // _ X _
            // X _ _
            Arrays.asList(
                    new Board.Coordinate(0, 2),
                    new Board.Coordinate(1, 1),
                    new Board.Coordinate(2, 0)
            )
    );

    private final Board board;

    public WinningMoves(Board board) {
        this.board = board;
    }

    public boolean isWin(Game.Player player) {
        for (List<Board.Coordinate> coordinates : winningMoves) {
            Board.Coordinate first = coordinates.get(0);
            Board.Coordinate second = coordinates.get(1);
            Board.Coordinate third = coordinates.get(2);

            if (player.equals(board.cells[first.getX()][first.getY()]) &&
                player.equals(board.cells[second.getX()][second.getY()]) &&
                player.equals(board.cells[third.getX()][third.getY()]))
               return true;
        }
        return false;
    }
}
