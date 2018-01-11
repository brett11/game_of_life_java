package com.brett.view;

import javax.swing.*;

public class App
{
    public static void main( String[] args ){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MainFrame("Game of Life");
        });
    }
}
