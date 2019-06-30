package com.grantburgess;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PrimeFactorTest {
    private List<Integer> listOf(Integer... ints) {
        return Arrays.asList(ints);
    }

    private void assertPrimeFactor(List<Integer> expected, int factorsOf) {
        assertEquals(expected, PrimeFactor.of(factorsOf));
    }

    @Test
    public void factorsOf() {
        assertPrimeFactor(listOf(), 1);
        assertPrimeFactor(listOf(2), 2);
        assertPrimeFactor(listOf(3), 3);
        assertPrimeFactor(listOf(2, 2), 4);
        assertPrimeFactor(listOf(2, 3), 6);
        assertPrimeFactor(listOf(2, 2, 2), 8);
        assertPrimeFactor(listOf(3, 3), 9);
    }
}
