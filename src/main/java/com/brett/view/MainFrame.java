package com.brett.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Toolbar toolbar;

    public MainFrame(String title){
        super(title);

        setLayout(new BorderLayout());

        toolbar = new Toolbar();

        //add subcomponents
        add(toolbar, BorderLayout.NORTH);

        //standard
        setMinimumSize(new Dimension(500, 400));
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
