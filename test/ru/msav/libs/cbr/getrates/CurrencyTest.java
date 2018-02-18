/*
 * The MIT License
 *
 * Copyright 2018 msav.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ru.msav.libs.cbr.getrates;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msav
 */
public class CurrencyTest {
    
    private Currency CurrencyObject;
    
    /**
     * Initialize a simple currency object.
     */
    public CurrencyTest() {
        CurrencyObject = new Currency();
        CurrencyObject.ID = "R01235";
        CurrencyObject.NumCode = "840";
        CurrencyObject.CharCode = "USD";
        CurrencyObject.Nominal = 1.0;
        CurrencyObject.Name = "Доллар США";
        CurrencyObject.Rate = 56.98;
    }
    
    /**
     * Test a simple currency object.
     */
    @Test
    public void testCurrencyConstructor() {
        assertEquals("Unexpected USD currentcy ID", CurrencyObject.ID, "R01235");
        assertEquals("Unexpected USD currentcy NumCode", CurrencyObject.NumCode, "840");
        assertEquals("Unexpected USD currentcy CharCode", CurrencyObject.CharCode, "USD");
        assertEquals("Unexpected USD currentcy Nominal", Double.compare(CurrencyObject.Nominal, 1.0), 0);
        assertEquals("Unexpected USD currentcy Name", CurrencyObject.Name, "Доллар США");
        assertEquals("Unexpected USD currentcy Rate", Double.compare(CurrencyObject.Rate, 56.98), 0);
    }
    
}
