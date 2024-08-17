package org.cis1200;

import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.BufferedReader;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/** Tests for LineIterator */
public class LineIteratorTest {

    /*
     * Here's a test to help you out, but you still need to write your own.
     */

    @Test
    public void testHasNextAndNext() {

        // Note we don't need to create a new file here in order to test out our
        // LineIterator if we do not want to. We can just create a
        // StringReader to make testing easy!
        String words = "0, The end should come here.\n"
                + "1, This comes from data with no duplicate words!";
        StringReader sr = new StringReader(words);
        BufferedReader br = new BufferedReader(sr);
        LineIterator li = new LineIterator(br);
        assertTrue(li.hasNext());
        assertEquals("0, The end should come here.", li.next());
        assertTrue(li.hasNext());
        assertEquals("1, This comes from data with no duplicate words!", li.next());
        assertFalse(li.hasNext());
    }

    /* **** ****** **** WRITE YOUR TESTS BELOW THIS LINE **** ****** **** */
    @Test
    void passingNullFilePath() {
        String filePath = null;
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new LineIterator(filePath);
                }
        );
    }

    @Test
    void passingNullBufferReader() {
        BufferedReader reader = null;
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new LineIterator(reader);
                }
        );
    }

    @Test
    public void testNextEmptyLine() {
        String words = "0, The end should come here.";
        StringReader sr = new StringReader(words);
        BufferedReader br = new BufferedReader(sr);
        LineIterator li = new LineIterator(br);
        li.next();
        assertThrows(
                NoSuchElementException.class,
                () -> {
                    li.next();
                }
        );
        assertFalse(li.hasNext());
    }

    @Test
    public void testHasNextMultipleTimes() {

        String words = "0, The end should come here.";
        StringReader sr = new StringReader(words);
        BufferedReader br = new BufferedReader(sr);
        LineIterator li = new LineIterator(br);
        assertTrue(li.hasNext());
        assertTrue(li.hasNext());
        assertTrue(li.hasNext());
        li.next();
        assertFalse(li.hasNext());
        assertFalse(li.hasNext());
        assertFalse(li.hasNext());
        assertFalse(li.hasNext());
    }

}
