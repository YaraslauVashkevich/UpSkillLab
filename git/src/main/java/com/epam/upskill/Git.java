package com.epam.upskill;

import javax.swing.JOptionPane;

public class Git {
    public static void main(String[] args) {
        for (; ; ) {
            String in = JOptionPane.showInputDialog(null, "Input name:");
            if (in != null) {
                in = in.strip();
            }
            if (in != null && !in.equals("")) {
                in = in.strip();
                JOptionPane.showMessageDialog(null, "Hello, " + in + "! Nice to meet you! How are you doing? What time is it?");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "You didn't enter a name!");
            }
        }
    }
}
