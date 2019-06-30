package com.grantburgess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NameInverterTest {
    private NameInverter nameInverter;

    private void assertInverted(String originalName, String invertedName) {
        assertEquals(invertedName, nameInverter.invertName(originalName));
    }

    @Before
    public void setUp() {
        nameInverter = new NameInverter();
    }

    @Test
    public void null_returns_empty_string() {
        assertInverted(null, "");
    }

    @Test
    public void empty_string_returns_empty_string() {
        assertInverted("", "");
    }

    @Test
    public void spaces_returns_empty_string() {
        assertInverted("  ", "");
    }

    @Test
    public void simple_name_returns_simple_name() {
        assertInverted("Name", "Name");
    }

    @Test
    public void simple_name_with_spaces_returns_simple_name() {
        assertInverted("  Name  ", "Name");
    }

    @Test
    public void first_last_returns_last_comma_first() {
        assertInverted("First Last", "Last, First");
    }

    @Test
    public void first_last_with_extra_spaces_returns_last_first_without_extra_spaces() {
        assertInverted("  First  Last  ", "Last, First");
    }

    @Test
    public void ignore_honorifics() {
        assertInverted("Mr. First Last", "Last, First");
        assertInverted("Mrs. First Last", "Last, First");
        assertInverted("Dr. First Last", "Last, First");
    }

    @Test
    public void post_nominals_stay_at_end() {
        assertInverted("First Last PhD.", "Last, First PhD.");
        assertInverted("First Last M.B Ch.B PhD.", "Last, First M.B Ch.B PhD.");
    }

    @Test
    public void integration() {
        assertInverted("  Dr. Robert Martin esq. MBE", "Martin, Robert esq. MBE");
    }
}
