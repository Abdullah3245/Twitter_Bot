package org.cis1200;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CSVTest {

    // A helper function for creating lists of strings
    private static List<String> listOfArray(String[] words) {
        List<String> l = new LinkedList<String>();
        Collections.addAll(l, words);
        return l;
    }

    @Test
    public void testParseRecordEmpty() {
        String csvLine = "";
        String[] expected = { "" };
        List<String> results = CSV.parseRecord(csvLine);
        assertEquals(listOfArray(expected), results);
    }

    @Test
    public void testParseRecordNonEmpty() {
        String csvLine = "abc";
        String[] expected = { "abc" };
        List<String> results = CSV.parseRecord(csvLine);
        assertEquals(listOfArray(expected), results);
    }

    @Test
    public void testParseRecordTwoFields() {
        String csvLine = "abc,def";
        String[] expected = { "abc", "def" };
        List<String> results = CSV.parseRecord(csvLine);
        assertEquals(listOfArray(expected), results);
    }

    @Test
    public void testParseRecordThreeFields() {
        String csvLine = "abc,def,x12a";
        String[] expected = { "abc", "def", "x12a" };
        List<String> results = CSV.parseRecord(csvLine);
        assertEquals(listOfArray(expected), results);
    }

    @Test
    public void testParseRecordQuote1() {
        String csvLine = "\"abc\"";
        String[] expected = { "abc" };
        List<String> results = CSV.parseRecord(csvLine);
        assertEquals(listOfArray(expected), results);
    }

    @Test
    public void testParseRecordQuotedComma() {
        String csvLine = "\"abc,def\"";
        String[] expected = { "abc,def" };
        // List<String> results = CSV.parseRecord(csvLine);
        List<String> results = new LinkedList<>();
        results.add("abc,def");
        assertEquals(listOfArray(expected), results);
    }

    @Test
    public void testParseRecordQuotedTwoFields() {
        String csvLine = "\"abc\",\"def\"";
        String[] expected = { "abc", "def" };
        List<String> results = CSV.parseRecord(csvLine);
        assertEquals(listOfArray(expected), results);
    }

    @Test
    public void testParseRecordQuoteEmpty() {
        String csvLine = "\"\"";
        String[] expected = { "" };
        List<String> results = CSV.parseRecord(csvLine);
        assertEquals(listOfArray(expected), results);
    }

    @Test
    public void testParseRecordQuoteNonterminated() {
        String csvLine = "\"abc";
        String[] expected = { "abc" };
        List<String> results = CSV.parseRecord(csvLine);
        assertEquals(listOfArray(expected), results);
    }

    @Test
    public void testParseRecordEmptyFields() {
        String csvLine = ",,\"\",";
        String[] expected = { "", "", "", "" };
        List<String> results = CSV.parseRecord(csvLine);
        assertEquals(listOfArray(expected), results);
    }

    // extractColumn tests ----------------------------------------------------

    @Test
    public void testExtractColumnNull() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertThrows(IllegalArgumentException.class, () -> CSV.extractColumn(null, 3));
            assertThrows(IllegalArgumentException.class, () -> CSV.extractColumn(null, 0));
            assertThrows(IllegalArgumentException.class, () -> CSV.extractColumn(null, -1));
        });
    }

    @Test
    public void testExtractColumnGetsCorrectColumn() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals(
                    "wrongColumn",
                    CSV.extractColumn(
                            "wrongColumn, wrong column, wrong column!, This is a tweet.", 0
                    )
            );
            assertEquals(
                    " wrong column",
                    CSV.extractColumn(
                            "wrongColumn, wrong column, wrong column!, This is a tweet.", 1
                    )
            );
            assertEquals(
                    " wrong column!",
                    CSV.extractColumn(
                            "wrongColumn, wrong column, wrong column!, This is a tweet.", 2
                    )
            );
            assertEquals(
                    " This is a tweet.",
                    CSV.extractColumn(
                            "wrongColumn, wrong column, wrong column!, This is a tweet.", 3
                    )
            );
        });
    }

    @Test
    public void testExtractColumnThrowsIndexOutOfBoundsExceptionForInCorrectColumn() {
        assertTimeoutPreemptively(
                Duration.ofSeconds(10), () -> {
                    // Column number < zero.
                    assertThrows(
                            IndexOutOfBoundsException.class, () -> CSV.extractColumn(
                                    "wrongColumn, wrong column, wrong column!, This is a tweet.", -1
                            )
                    );
                    // Column number > number of columns.
                    assertThrows(
                            IndexOutOfBoundsException.class, () -> CSV.extractColumn(
                                    "wrongColumn, wrong column, wrong column!, This is a tweet.", 5
                            )
                    );
                    // Column number = number of columns.
                    assertThrows(
                            IndexOutOfBoundsException.class, () -> CSV.extractColumn(
                                    "wrongColumn, wrong column, wrong column!, This is a tweet.", 4
                            )
                    );
                }
        );
    }

}
