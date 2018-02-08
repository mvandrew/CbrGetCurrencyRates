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

import java.util.Date;

/**
 * Exchange rates collection and scraping functions
 * 
 * @author msav
 */
public class Rates {
    
    /**
     * Query language
     */
    public QueryLanguage queryLanguage;
    
    /**
     * Date of exchange rates
     */
    private Date ratesDate;
    
    /**
     * Returns the date of exchange rates.
     * 
     * @return Date of exchange rates
     */
    public Date getRatesDate() {
        return ratesDate;
    }
    
    /**
     * Default constructor
     */
    public Rates() {
        queryLanguage = QueryLanguage.Russian;
        ratesDate = null;
    }
    
    /**
     * Language constructor
     * 
     * @param ql query language
     */
    public Rates(QueryLanguage ql) {
        this();
        queryLanguage = ql;
    }
    
    /**
     * Returns the URL of the service, depending on the query language.
     * 
     * @return service URL
     */
    public String getServiceURL() {
        String result;
        switch (queryLanguage) {
            case English:
                result = "http://www.cbr.ru/scripts/XML_daily_eng.asp";
                break;
            default:
                result = "http://www.cbr.ru/scripts/XML_daily.asp";
                break;
        }
        return result;
    }
    
}
