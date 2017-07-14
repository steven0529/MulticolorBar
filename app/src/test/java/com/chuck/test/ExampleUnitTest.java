package com.chuck.test;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void isColorGenerationCorrect() throws Exception {
        assertNotEquals("bf342c", ColorDictionary.getRandomColor("bf342c"));
    }
}