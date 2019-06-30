package com.grantburgess.tennis;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {
    private int playerOneScore;
    private int playerTwoScore;

    public List<String> getScore() {
        if (hasWon())
            return printWinner();
        else if (hasAdvantage())
            return printAdvantage();
        else if (hasDeuce())
            return printDeuce();
        else
            return printRegularScore();
    }

    private boolean hasWon() {
        return playerOneWins() || playerTwoWins();
    }
    private boolean playerOneWins() {
        return playerOneScore > 3 && playerOneScore - playerTwoScore >= 2;
    }

    private boolean playerTwoWins() {
        return playerTwoScore > 3 && playerTwoScore - playerOneScore >= 2;
    }

    private List<String> printWinner() {
        String formatString = "Player %s wins game";
        int player = playerOneWins() ?
                1 : 2;
        return Collections.singletonList(String.format(formatString, player));
    }

    private boolean hasAdvantage() {
        return playerOneHasAdvantage() || playerTwoHasAdvantage();
    }

    private List<String> printAdvantage() {
        if (playerOneHasAdvantage())
            return Arrays.asList("Advantage Player One");

        return Arrays.asList("Advantage Player Two");
    }

    private boolean playerOneHasAdvantage() {
        return playerOneScore > 3 && playerOneScore - playerTwoScore == 1;
    }

    private boolean playerTwoHasAdvantage() {
        return playerTwoScore > 3 && playerTwoScore - playerOneScore == 1;
    }

    private boolean hasDeuce() {
        return playerOneScore == playerTwoScore && playerOneScore >= 3;
    }

    private List<String> printDeuce() {
        return Arrays.asList("deuce");
    }

    private List<String> printRegularScore() {
        return Arrays.asList(getScore(0), getScore(1));
    }

    private String getScore(int player) {
        String result = "";
        int playerScore = player == 0 ? playerOneScore : playerTwoScore;

        switch (playerScore) {
            case 0:
                result = "0";
                break;
            case 1:
                result = "15";
                break;
            case 2:
                result = "30";
                break;
            case 3:
            default:
                result = "40";
        }

        return result;
    }

    public void player1Scored() {
        playerOneScore++;
    }

    public void player2Scored() {
        playerTwoScore++;
    }
}
