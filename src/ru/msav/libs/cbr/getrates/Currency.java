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

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Currency data class.
 * 
 * @author msav
 */
public class Currency {
    
    /**
     * CBR currency ID
     */
    public String ID;
    
    /**
     * International currency numeric code.
     */
    public String NumCode;
    
    /**
     * International currency character code.
     */
    public String CharCode;
    
    /**
     * Nominal exchange rate.
     */
    public Double Nominal;
    
    /**
     * Localized currency name.
     */
    public String Name;
    
    /**
     * Exchange rate.
     */
    public Double Rate;
    
    /**
     * Default Costructor
     */
    public Currency() {
        ID = "";
        NumCode = "";
        CharCode = "";
        Nominal = 1.0;
        Name = "";
        Rate = 0.0;
    }
    
    /**
     * A currency constructor using an XML node.
     * @param currencyNode 
     */
    public Currency(Node currencyNode) {
        this();
        
        NodeList nodeList = currencyNode.getChildNodes();
        int length = nodeList.getLength();
        Node curNode;
        for (int i = 0; i < length; i++) {
            curNode = nodeList.item(i);
            switch (curNode.getNodeName()) {
                case "CharCode":
                    CharCode = curNode.getFirstChild().getNodeValue();
                    break;
                case "Value":
                    Rate = Double.parseDouble(curNode.getFirstChild().getNodeValue().replace(',', '.'));
                    break;
            }
        }
    }
    
}
