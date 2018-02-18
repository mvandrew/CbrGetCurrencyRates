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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
     * Currencies list
     */
    public ArrayList<Currency> currencyCollection;
    
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
    
    /**
     * Fills the currencies collection.
     */
    public void fillCollection() {
        Document xmlDocument = getXMLDocument();
        if (xmlDocument != null) {
            
            NodeList nodeList = xmlDocument.getChildNodes();
            int length = nodeList.getLength();
            
            if (length > 0) {
                Node rootNode = nodeList.item(0);
                nodeList = rootNode.getChildNodes();
                length = nodeList.getLength();
                Node curNode;
                for (int i = 0; i < length; i++) {
                    curNode = nodeList.item(i);
                    if (curNode.getNodeName().equals("Valute")) {
                        Currency currency = new Currency(curNode);
                    }
                }
            } 
        }
    }
    
    private DocumentBuilder getDocumentBuilder() {
        DocumentBuilder builder;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            builder = null;
            Logger.getLogger(Rates.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return builder;
    }
    
    public Document getXMLDocument() {
        Document xmlDocument = null;
        DocumentBuilder builder = getDocumentBuilder();
        
        if (builder != null) {
            try {
                xmlDocument = builder.parse(getServiceURL());
            } catch (SAXException ex) {
                Logger.getLogger(Rates.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Rates.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return xmlDocument;
    }
    
    /**
     * Returns an exchange rate instance using the Russian locale.
     * 
     * @return Instance of exchange rates
     */
    public static Rates getInstance() {
        return getInstance(QueryLanguage.Russian);
    }
    
    /**
     * Returns an exchange rate instance using the specified locale.
     * 
     * @param ql Query language
     * @return Instance of exchange rates
     */
    public static Rates getInstance(QueryLanguage ql) {
        Rates rates = new Rates(ql);
        rates.fillCollection();
        return rates;
    }
    
}
