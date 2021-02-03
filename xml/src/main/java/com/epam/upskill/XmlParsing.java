package com.epam.upskill;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class XmlParsing {
    private static Logger logger = LogManager.getLogger();

    public String getSuffix(String path, String nameConfig) {

        StringBuilder xml = new StringBuilder();
        try {
            FileReader in = new FileReader(path);
            int ch = in.read();
            while (ch != -1) {
                xml = xml.append((char) ch);
                ch = in.read();
            }
            in.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Configuration file not found!");
            logger.log(Level.ERROR, "Configuration file not found.", e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error!");
            logger.log(Level.ERROR, "Error file.", e);
        }

        String newXml = new String(xml);
        String tag = "<" + "suffix" + ">";
        int start = newXml.indexOf(tag);
        if (start < 0) {
            logger.log(Level.ERROR, "Suffix not found.");
            return "";
        } else {
            start += tag.length();
        }
        int end = newXml.lastIndexOf("</" + "suffix" + ">");
        if (end < 0) {
            logger.log(Level.ERROR, "Suffix not found.");
            return "";
        }
        newXml = newXml.substring(start, end);
        logger.log(Level.INFO, "The configuration file [" + nameConfig + "] is read.");
        return newXml;
    }

    public String getSuffixDom(String path, String nameConfig) {
        String suffix = "";
        try {
            File xmlFile = new File(path);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);
            NodeList nl = doc.getElementsByTagName("configuration");
            for (int i = 0; i < nl.getLength(); i++) {
                Node node = nl.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    suffix = element.getElementsByTagName("suffix").item(0).getChildNodes().item(0).getNodeValue();
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | NullPointerException e) {
            logger.log(Level.ERROR, "Suffix not found.");
            e.printStackTrace();
        }
        logger.log(Level.INFO, "The configuration file [" + nameConfig + "] is read.");
        return suffix;

    }

    public String getSuffixSax(String path, String nameConfig) {
        String suffix = "";
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SaxParsing handler = new SaxParsing();
            saxParser.parse(path, handler);
            suffix = handler.getSuffix();
        } catch (Exception e) {
            logger.log(Level.ERROR, "Suffix not found.");
            e.printStackTrace();
        }
        logger.log(Level.INFO, "The configuration file [" + nameConfig + "] is read.");
        return suffix;
    }
}
