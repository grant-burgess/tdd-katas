package com.grantburgess.tictactoe;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

// Work in progress
public class GameUITest {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().mute().enableLog();
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    private GameUI gameUI;

    @Before
    public void setUp() throws Exception {
        gameUI = new GameUI(System.in, System.out);
    }

    @Test
    public void board_is_empty_on_creation() {
        gameUI.renderBoard();

        assertEquals("" +
                        "   |   |   \n" +
                        "-----------\n" +
                        "   |   |   \n" +
                        "-----------\n" +
                        "   |   |   \n" +
                        "",
                systemOutRule.getLog());
    }

    @Test
    public void can_accept_valid_input() {
        systemInMock.provideLines("0 0");
        gameUI.promptForMove();
        systemOutRule.clearLog();

        gameUI.renderBoard();

        assertEquals("" +
                        " X |   |   \n" +
                        "-----------\n" +
                        "   |   |   \n" +
                        "-----------\n" +
                        "   |   |   \n" +
                        "",
                systemOutRule.getLog());
    }

    @Test(expected = GameUI.BadInput.class)
    public void null_input_not_allowed() {
        systemInMock.provideLines();
        gameUI.promptForMove();
    }

    @Test(expected = GameUI.BadInput.class)
    public void empty_string_input_not_allowed() {
        systemInMock.provideLines("");
        gameUI.promptForMove();
    }

    @Test(expected = GameUI.BadInput.class)
    public void spaces_input_not_allowed() {
        systemInMock.provideLines("  ");
        gameUI.promptForMove();
    }

    @Test(expected = GameUI.BadInput.class)
    public void characters_not_allowed() {
        systemInMock.provideLines("x");
        gameUI.promptForMove();
    }

    @Test
    @Ignore
    public void check_for_exist_command() {
        systemInMock.provideLines("y");
        gameUI.promptForMove();
    }
}
