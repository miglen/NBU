/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cscb869;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import com.cscb869.HelperMethods;

/**
 *
 * @author Miglen Evlogiev
 */
public class HelperMethodsTest {

    private static final String NOT_IMPLEMENTED = "NOT IMPLEMENTED";

    private void notImplemented() {
        fail(NOT_IMPLEMENTED);
    }

    @Test
    public void getNumberOfWordsTest() {
        assertEquals(4, HelperMethods.getNumberOfWords("this is my test"));
        assertEquals(3, HelperMethods.getNumberOfWords("this is a test!"));
        assertEquals(1, HelperMethods.getNumberOfWords("this"));
        assertEquals(2, HelperMethods.getNumberOfWords("this is !@#$%"));
        assertEquals(0, HelperMethods.getNumberOfWords("!@#$% .... !"));
    }

    @Test
    public void getNumberOfSymbolsTest() {
        assertEquals(8, HelperMethods.getNumberOfSymbols("this is my test"));
        assertEquals(8, HelperMethods.getNumberOfSymbols("this is a test!"));
        assertEquals(4, HelperMethods.getNumberOfSymbols("this"));
        assertEquals(10, HelperMethods.getNumberOfSymbols("this is !@#$%"));
        assertEquals(7, HelperMethods.getNumberOfSymbols("!@#$% .... !"));
    }

    @Test
    public void invertStringTest() {
        assertEquals("tset ym si siht", HelperMethods.invertString("this is my test"));
        assertEquals("!tset a si siht", HelperMethods.invertString("this is a test!"));
        assertEquals("siht", HelperMethods.invertString("this"));
        assertEquals("a", HelperMethods.invertString("a"));
    }

    @Test
    public void findSubstrTest() {
        assertEquals(8, HelperMethods.findSubstr("this is my test this is my test this is my test", "my"));
        assertEquals(14, HelperMethods.findSubstr("this is a test!", "!"));
        assertEquals(2, HelperMethods.findSubstr("this", "is"));
        assertEquals(-1, HelperMethods.findSubstr("a", "hey"));
    }

}
