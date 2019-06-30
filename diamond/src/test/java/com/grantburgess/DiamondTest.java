package com.grantburgess;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.Arrays;

import static com.grantburgess.Diamond.PADDING;
import static com.grantburgess.Diamond.SYMBOL;
import static org.junit.Assert.assertEquals;

public class DiamondTest {
    private void assertDiamondRow(String expected, int size, int row) {
        String[] diamond = Diamond.of(size);
        assertEquals(MessageFormat.format(expected, PADDING, SYMBOL), diamond[row]);
    }

    private void printDiamond(int size) {
        Arrays.stream(Diamond.of(size)).forEach(System.out::println);
    }

    @Test
    public void zero_size_should_have_empty_first_row() {
        assertDiamondRow("", 0, 0);
    }

    @Test
    public void size_of_one_first_row_should_have_no_spaces() {
        assertDiamondRow(SYMBOL, 1, 0);
    }

    @Test
    public void size_of_two_first_row_should_have_one_outer_space() {
        assertDiamondRow("{0}{1}{0}", 2, 0);
    }

    @Test
    public void size_of_three_first_row_should_have_two_outer_space() {
        assertDiamondRow("{0}{0}{1}{0}{0}", 3, 0);
    }

    @Test
    public void size_of_two_second_row_should_have_one_inner_space() {
        assertDiamondRow("{1}{0}{1}", 2, 1);
    }

    @Test
    public void size_of_three_third_row_should_have_two_inner_space() {
        assertDiamondRow("{1}{0}{0}{0}{1}", 3, 2);
    }

    @Test
    public void size_of_four_fourth_row_should_have_five_inner_space() {
        assertDiamondRow("{1}{0}{0}{0}{0}{0}{1}", 4, 3);
    }

    @Test
    public void size_of_three_second_row_has_one_inner_space_and_one_outer_space() {
        assertDiamondRow("{0}{1}{0}{1}{0}", 3, 1);
    }

    @Test
    public void size_of_four_second_row_has_one_inner_space_and_two_outer_space() {
        assertDiamondRow("{0}{0}{1}{0}{1}{0}{0}", 4, 1);
    }

    @Test
    public void size_of_four_third_row_has_three_inner_space_and_one_outer_space() {
        assertDiamondRow("{0}{1}{0}{0}{0}{1}{0}", 4, 2);
    }

    @Test
    public void size_four_half_way() {
        int size = 4;
        assertDiamondRow("{0}{0}{0}{1}{0}{0}{0}", size, 0);
        assertDiamondRow("{0}{0}{1}{0}{1}{0}{0}", size, 1);
        assertDiamondRow("{0}{1}{0}{0}{0}{1}{0}", size, 2);
        assertDiamondRow("{1}{0}{0}{0}{0}{0}{1}", size, 3);
    }

    @Test
    public void size_four_full() {
        int size = 4;
        printDiamond(size);
        assertDiamondRow("{0}{0}{0}{1}{0}{0}{0}", size, 0);
        assertDiamondRow("{0}{0}{1}{0}{1}{0}{0}", size, 1);
        assertDiamondRow("{0}{1}{0}{0}{0}{1}{0}", size, 2);
        assertDiamondRow("{1}{0}{0}{0}{0}{0}{1}", size, 3);
        assertDiamondRow("{0}{1}{0}{0}{0}{1}{0}", size, 4);
        assertDiamondRow("{0}{0}{1}{0}{1}{0}{0}", size, 5);
        assertDiamondRow("{0}{0}{0}{1}{0}{0}{0}", size, 6);
    }
}
