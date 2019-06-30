package com.grantburgess;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordWrapTest {
    private void assertWrap(String input, String expected, int length) {
        assertEquals(expected, WordWrap.wrap(input, length));
    }

    @Test
    public void should_not_wrap() {
        assertWrap(null, "", 1);
        assertWrap("", "", 1);
        assertWrap(" ", "", 1);
        assertWrap("x", "x", 1);
        assertWrap("x", "x", 3);
    }

    @Test
    public void should_wrap() {
        assertWrap("xx", "x\nx", 1);
        assertWrap("xxx", "x\nx\nx", 1);
        assertWrap("x x x", "x\nx\nx", 1);
        assertWrap("x xxx", "x\nxxx", 3);
        assertWrap("word word", "word\nword", 4);
        assertWrap("word word", "word\nword", 5);
        assertWrap("word", "wor\nd", 3);
    }

    @Test
    public void integration() {
        assertWrap("Lorem ipsum dolor sit amet, eam virtute scaevola ne. An sumo dolor intellegam cum. Duo laoreet accusamus ex, in ius habeo porro. At nec accusam sensibus torquatos. Adipiscing contentiones cu mea, ea harum graece eam. Ex eam modus copiosae iracundia, per an facete impedit laboramus.",
                "Lorem ipsum dolor sit amet, eam virtute scaevola ne. An sumo dolor intellegam cum.\n" +
                "Duo laoreet accusamus ex, in ius habeo porro. At nec accusam sensibus torquatos.\n" +
                "Adipiscing contentiones cu mea, ea harum graece eam. Ex eam modus copiosae\n" +
                "iracundia, per an facete impedit laboramus.",
                83);
    }
}
