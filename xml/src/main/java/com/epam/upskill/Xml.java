package com.epam.upskill;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Xml {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.log(Level.INFO, "The application is running.");

        JFileChooser choiceFile = new JFileChooser();
        choiceFile.setDialogTitle("Open file configuration");
        int selected = choiceFile.showOpenDialog(null);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File selectFile = choiceFile.getSelectedFile();
            String path = selectFile.getAbsolutePath();
            logger.log(Level.INFO, "The configuration file is selected.");

            StringBuilder suffix = new StringBuilder();
            try {
                FileReader in = new FileReader(path);
                int ch = in.read();
                while (ch != -1) {
                    suffix = suffix.append((char) ch);
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
            logger.log(Level.INFO, "The configuration file is read.");

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
                        System.out.println(name + " --> " + newName);
                    }
                }
                logger.log(Level.INFO, "Renaming completed.");
            }
        }
        logger.log(Level.INFO, "The application is finished.");
    }
}

