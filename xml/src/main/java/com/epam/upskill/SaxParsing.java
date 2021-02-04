package com.epam.upskill;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParsing extends DefaultHandler {
    private String suffix = "";
    private boolean tag = false;

    public String getSuffix() {
        return suffix;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase(Constant.SUFFIX)) {
            tag = true;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if (tag) {
            suffix = new String(ch, start, length);
            tag = false;
        }
    }
}
