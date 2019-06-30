package com.grantburgess.tictactoe;

import java.util.Arrays;

public class Game {
    final Board board = new Board();
    private final WinningMoves winningMoves = new WinningMoves(board);
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

        checkForDraw();
        checkForWin(player);
    }

    private void checkForDraw() {
        if (isDraw())
            currentStatus = Status.DRAW;
    }

    private void checkForWin(Player player) {
        if (winningMoves.isWin(player))
            currentStatus = Player.CROSS.equals(player) ?
                    Status.CROSS_WINS :
                    Status.NAUGHT_WINS;
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

    public class MoveOutOfOrder extends RuntimeException { }

    public class GameOver extends RuntimeException { }
}
