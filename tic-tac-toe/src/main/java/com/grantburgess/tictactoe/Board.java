package com.grantburgess.tictactoe;

public class Board {
    Game.Player[][] cells = new Game.Player[3][3];

    void takeCell(Game.Player player, Coordinate coordinate) {
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
