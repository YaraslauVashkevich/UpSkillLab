package com.epam.upskill;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLOutputFactory;

import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;

public class XmlSave {
    private static Logger logger = LogManager.getLogger();

    public void xmlSave(String nameConfig, String leadTime, String oldFile, String newFile) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element rootElement = doc.createElement("result");
            doc.appendChild(rootElement);

            Element configuration = doc.createElement("configuration");
            rootElement.appendChild(configuration);

            Element name_config = doc.createElement("name_config");
            name_config.setTextContent(nameConfig);
            configuration.appendChild(name_config);
            Element lead_time = doc.createElement("lead_time");
            lead_time.setTextContent(leadTime);
            configuration.appendChild(lead_time);

            Element old_file = doc.createElement("old_file");
            rootElement.appendChild(old_file);
            Element new_name = doc.createElement("name");
            new_name.setTextContent(oldFile);
            old_file.appendChild(new_name);

            Element new_file = doc.createElement("new_file");
            rootElement.appendChild(new_file);
            Element old_name = doc.createElement("name");
            old_name.setTextContent(newFile);
            new_file.appendChild(old_name);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult sr;
            sr = new StreamResult("src\\main\\resources\\resultDom.xml");
            t.transform(source, sr);
            sr = new StreamResult(System.out);
            t.transform(source, sr);
            System.out.println();
        } catch (Exception e) {
            logger.log(Level.ERROR, "The XML file is not created", e);
        }
        logger.log(Level.INFO, "File [resultDom.xml] is created.");
    }

    public void xmlSaveStax(String nameConfig, String leadTime, String oldFile, String newFile) {
        try {
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            FileWriter fileWriter = new FileWriter("src\\main\\resources\\resultStax.xml");
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(fileWriter);

            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("result");
            writer.writeStartElement("configuration");
            writer.writeStartElement("name_config");
            writer.writeCharacters(nameConfig);
            writer.writeEndElement();
            writer.writeStartElement("lead_time");
            writer.writeCharacters(leadTime);
            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeStartElement("old_file");
            writer.writeStartElement("name");
            writer.writeCharacters(oldFile);
            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeStartElement("new_file");
            writer.writeStartElement("name");
            writer.writeCharacters(newFile);
            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.log(Level.ERROR, "The XML file is not created", e);
        }
        logger.log(Level.INFO, "File [resultStax.xml] is created.");
    }
}

