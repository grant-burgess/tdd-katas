package com.grantburgess.tictactoe;

import java.util.Arrays;
import java.util.List;

public class Game {
    final Board board = new Board();
    private final WinningMoves winningMoves = new WinningMoves(this);
    private Status currentStatus = Status.NOT_STARTED;
    private Player currentPlayer = Player.CROSS;
    private int plays;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public enum Status {IN_PROGRESS, DRAW, CROSS_WINS, NAUGHT_WINS, NOT_STARTED}

    public enum Player {
        CROSS, NAUGHT;
        public Player inverse() {
            return this.equals(CROSS) ? NAUGHT : CROSS;
        }

        @Override
        public String toString() {
            return this.equals(CROSS) ? "X" : "O";
        }
    }

    public void naughtMoves(Board.Coordinate coordinate) {
        move(Player.NAUGHT, coordinate);
    }

    public void crossMoves(Board.Coordinate coordinate) {
        move(Player.CROSS, coordinate);
    }

    private void move(Player player, Board.Coordinate coordinate) {
        validatePlayer(player);
        board.takeCell(player, coordinate);
        setGameInProgressWhenNotStarted();
        currentPlayer = currentPlayer.inverse();
        plays++;

        if (isDraw())
            currentStatus = Status.DRAW;

        winningMoves.checkForWin(player);
    }

    private boolean isDraw() {
        return plays == 9;
    }

    private void validatePlayer(Player player) {
        checkGameOver();
        if (Player.NAUGHT.equals(player) && Status.NOT_STARTED.equals(currentStatus))
            throw new MoveOutOfOrder();
        if (!player.equals(currentPlayer))
            throw new MoveOutOfOrder();
    }

    private void checkGameOver() {
        if (Arrays.asList(Status.DRAW, Status.CROSS_WINS, Status.NAUGHT_WINS).contains(currentStatus))
            throw new GameOver();
    }

    private void setGameInProgressWhenNotStarted() {
        if (Status.NOT_STARTED.equals(currentStatus))
            currentStatus = Status.IN_PROGRESS;
    }

    public Status status() {
        return currentStatus;
    }

    public static class Board {
        Player[][] cells = new Player[3][3];

        void takeCell(Player player, Coordinate coordinate) {
            validateCoordinates(coordinate);
            cells[coordinate.getX()][coordinate.getY()] = player;
        }

        void validateCoordinates(Coordinate coordinate) {
            if (Math.max(coordinate.getX(), coordinate.getY()) > 2 ||
                    Math.min(coordinate.getX(), coordinate.getY()) < 0)
                throw new MoveOutOfBounds();
            if (cells[coordinate.getX()][coordinate.getY()] != null)
                throw new CellTaken();
        }

        public static class Coordinate {
            private final int x;
            private final int y;

            public Coordinate(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public int getY() {
                return y;
            }
        }

        class CellTaken extends RuntimeException {
        }

        class MoveOutOfBounds extends RuntimeException {
        }
    }

    public static class WinningMoves {
        private final Game game;

        public WinningMoves(Game game) {
            this.game = game;
        }

        private void checkForWin(Player player) {
            List<List<Board.Coordinate>> winningMoves = Arrays.asList(
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

            for (List<Board.Coordinate> coordinates : winningMoves) {
                Board.Coordinate first = coordinates.get(0);
                Board.Coordinate second = coordinates.get(1);
                Board.Coordinate third = coordinates.get(2);
                if (player.equals(game.board.cells[first.getX()][first.getY()]) &&
                        player.equals(game.board.cells[second.getX()][second.getY()]) &&
                        player.equals(game.board.cells[third.getX()][third.getY()]))
                    game.currentStatus = Player.CROSS.equals(player) ?
                            Status.CROSS_WINS :
                            Status.NAUGHT_WINS;
            }
        }
    }

    public class MoveOutOfOrder extends RuntimeException { }

    public class GameOver extends RuntimeException { }
}
