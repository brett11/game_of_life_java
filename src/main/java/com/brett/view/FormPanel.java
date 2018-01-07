package com.brett.view;

import javax.swing.*;
import java.awt.*;

public class FormPanel extends JPanel {
    private JLabel patternLabel;
    private JRadioButton gliderRadio;
    private JRadioButton blinkerRadio;
    private ButtonGroup patternGroup;
    private JButton okBtn;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 200;
        setPreferredSize(dim);

        patternLabel = new JLabel("Select Pattern:");
        gliderRadio = new JRadioButton("Glider");
        blinkerRadio = new JRadioButton("Blinker");
        patternGroup = new ButtonGroup();
        okBtn = new JButton("OK");

        //set up pattern radios
        gliderRadio.setActionCommand("Glider");
        blinkerRadio.setActionCommand("Blinker");
        patternGroup.add(gliderRadio);
        patternGroup.add(blinkerRadio);

        layoutComponents();
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        ////////////// Next row ///////////////////
        gc.weightx = 1;
        gc.weighty = 0.05;


        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(patternLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(gliderRadio, gc);

        ////////////// Next row ///////////////////
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 1;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(blinkerRadio, gc);

        ////////////// Next row ///////////////////
        gc.weightx = 1;
        gc.weighty = 2;


        gc.gridx = 1;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(okBtn, gc);
    }
}
