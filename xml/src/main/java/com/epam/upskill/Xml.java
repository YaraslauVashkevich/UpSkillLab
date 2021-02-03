package com.epam.upskill;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Xml {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.log(Level.INFO, "The application is running.");
        String nameConfig = "";
        String leadTime = "";
        StringBuilder oldFile = new StringBuilder();
        StringBuilder newFile = new StringBuilder();

        JFileChooser choiceFile = new JFileChooser();
        choiceFile.setDialogTitle("Open file configuration");
        int selected = choiceFile.showOpenDialog(null);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File selectFile = choiceFile.getSelectedFile();
            String path = selectFile.getAbsolutePath();
            nameConfig = selectFile.getName();
            logger.log(Level.INFO, "The configuration file is selected.");

            XmlParsing xmlParsing = new XmlParsing();
            //String suffix = xmlParsing.getSuffix(path);
            //String suffix = xmlParsing.getSuffixDom(path);
            String suffix = xmlParsing.getSuffixSax(path);

            JFileChooser choiceDirectory = new JFileChooser();
            choiceDirectory.setDialogTitle("Open directory");
            choiceDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = choiceDirectory.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectDirectory = choiceDirectory.getSelectedFile();
                String pathDirectory = selectDirectory.getAbsolutePath();
                logger.log(Level.INFO, "Directory selected.");

                File directory = new File(pathDirectory);
                File[] files = directory.listFiles();
                for (File file : files) {
                    if (file.exists() && file.isFile()) {
                        String name = file.getName();
                        int i = name.indexOf(".");
                        StringBuilder newName = new StringBuilder(name);
                        newName = newName.insert(i, suffix);
                        file.renameTo(new File(pathDirectory + "/" + newName));
                        oldFile = oldFile.append(name + "/");
                        newFile = newFile.append(newName + "/");
                    }
                }
                logger.log(Level.INFO, "Renaming completed.");
            }
        }
        String oldFiles = new String(oldFile);
        String newFiles = new String(newFile);
        Date data = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        leadTime = format.format(data);
        XmlSave xmlSave = new XmlSave();
        xmlSave.xmlSave(nameConfig, leadTime, oldFiles, newFiles);
        xmlSave.xmlSaveStax(nameConfig, leadTime, oldFiles, newFiles);
        logger.log(Level.INFO, "The application is finished.");
    }

}

