package com.grantburgess;

public class Diamond {

    public static final String SYMBOL = "X";
    public static final String PADDING = " ";
    private final int size;
    private final String[] diamond;
    private final int capacity;

    private Diamond(int size) {
        this.size = size;
        this.capacity = size == 0 ? 1 : (size * 2) - 1;
        diamond = new String[capacity];
    }

    public static String[] of(int size) {
        return new Diamond(size).make();
    }

    private String[] make() {
        if (size == 0)
            return new String[] { "" };
        if (size == 1)
            return new String[] { SYMBOL };

        makeHeader();
        makeRows();

        return diamond;
    }

    private void makeRows() {
        for (int row = 1; row < size; row++)
            makeRow(row);

        int currentRow = size;
        for (int i = size - 2; i >= 0; i--) {
            diamond[currentRow++] = diamond[i];
        }
    }

    private void makeRow(int row) {
        String outerPadding = createOuterPadding((size - 1) - row);
        String innerPadding = createInnerPadding(row);
        diamond[row] = outerPadding + SYMBOL + innerPadding + SYMBOL + outerPadding;
    }

    private String createInnerPadding(int row) {
        String result = "";
        for (int i = 0; i < (row * 2) - 1; i++) {
            result += PADDING;
        }
        return result;
    }

    private void makeHeader() {
        String outerPadding = createOuterPadding(size - 1);
        diamond[0] = outerPadding + SYMBOL + outerPadding;
    }

    private String createOuterPadding(int until) {
        String result = "";
        for (int i = 0; i < until; i++) {
            result += PADDING;
        }
        return result;
    }
}
