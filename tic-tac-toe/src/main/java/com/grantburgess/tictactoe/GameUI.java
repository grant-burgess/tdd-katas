package com.grantburgess.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.MessageFormat;

public class GameUI {
    private final InputStream in;
    private final PrintStream out;
    private Game game;

    public GameUI(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
        this.game = new Game();
    }

    public void renderBoard() {
        out.print(MessageFormat.format("" +
                        " {0} | {1} | {2} \n" +
                        "-----------\n" +
                        " {3} | {4} | {5} \n" +
                        "-----------\n" +
                        " {6} | {7} | {8} \n" +
                        "",
                renderCell(0, 0),
                renderCell(0, 1),
                renderCell(0, 2),

                renderCell(1, 0),
                renderCell(1, 1),
                renderCell(1, 2),

                renderCell(2, 0),
                renderCell(2, 1),
                renderCell(2, 2))
        );
    }

    private String renderCell(int x, int y) {
        Game.Player player = game.board.cells[x][y];
        return player == null ? " " : player.toString();
    }

    public void promptForMove() {
        String currentPlayer = game.getCurrentPlayer().equals(Game.Player.CROSS) ? "Cross" : "Naught";
        out.print(String.format("%s's move: ", currentPlayer));
        acceptInput();
    }

    private void acceptInput() {
        try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(in))) {
            String unparsedInput = bufferRead.readLine();
            validateInput(unparsedInput);

            String[] inputString = splitInput(unparsedInput);
            Game.Board.Coordinate coordinates = parseCoordinates(inputString);
            game.crossMoves(coordinates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateInput(String unparsedInput) {
        if (null == unparsedInput || "".equals(unparsedInput.trim()))
            throw new BadInput();

        String[] parts = splitInput(unparsedInput);

        if ("y".equals(parts[0]))
            return;

        ensureInputIsNumbers(parts);
    }

    private String[] splitInput(String unparsedInput) {
        return unparsedInput.toLowerCase().split(" ");
    }

    private void ensureInputIsNumbers(String[] parts) {
        for (int i = 0; i < parts.length; i++) {
            try {
                Integer.parseInt(parts[i]);
            } catch (NumberFormatException e) {
                throw new BadInput();
            }
        }
    }

    private Game.Board.Coordinate parseCoordinates(String[] coordinates) {
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);

        return new Game.Board.Coordinate(x, y);
    }

    public class BadInput extends RuntimeException { }
}
