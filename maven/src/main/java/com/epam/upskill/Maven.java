package com.epam.upskill;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Maven {
    public static void main(String[] args) {

        JFileChooser choiceFile = new JFileChooser();
        choiceFile.setDialogTitle("Open file configuration");
        int selected = choiceFile.showOpenDialog(null);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File selectFile = choiceFile.getSelectedFile();
            String path = selectFile.getAbsolutePath();

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
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error!");
            }

            JFileChooser choiceDirectory = new JFileChooser();
            choiceDirectory.setDialogTitle("Open directory");
            choiceDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = choiceDirectory.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectDirectory = choiceDirectory.getSelectedFile();
                String pathDirectory = selectDirectory.getAbsolutePath();

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
            }
        }
    }
}
