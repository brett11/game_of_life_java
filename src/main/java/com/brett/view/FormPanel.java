package com.brett.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {
    private JLabel patternLabel;
    private JRadioButton gliderRadio;
    private JRadioButton blinkerRadio;
    private JRadioButton spaceshipRadio;
    private JRadioButton pentaRadio;
    private JRadioButton pulsarRadio;
    private ButtonGroup patternGroup;
    private JButton okBtn;
    private JLabel countLabel;
    private JLabel countAmountLabel;
    private int generationCount;
    private FormListener formListener;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 200;
        setPreferredSize(dim);

        patternLabel = new JLabel("Select Pattern:");
        gliderRadio = new JRadioButton("Glider");
        blinkerRadio = new JRadioButton("Blinker");
        spaceshipRadio = new JRadioButton("Spaceship");
        pentaRadio = new JRadioButton("Pentadecatholon");
        pulsarRadio = new JRadioButton("Pulsar");
        patternGroup = new ButtonGroup();
        okBtn = new JButton("OK");
        countLabel = new JLabel("Generations:");
        countAmountLabel = new JLabel("0");

        //set up pattern radios
        gliderRadio.setActionCommand("Glider");
        gliderRadio.setSelected(true);
        blinkerRadio.setActionCommand("Blinker");
        spaceshipRadio.setActionCommand("Spaceship");
        pentaRadio.setActionCommand("Pentadecatholon");
        pulsarRadio.setActionCommand("Pulsar");
        patternGroup.add(gliderRadio);
        patternGroup.add(blinkerRadio);
        patternGroup.add(spaceshipRadio);
        patternGroup.add(pentaRadio);
        patternGroup.add(pulsarRadio);

        layoutComponents();

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pattern = patternGroup.getSelection().getActionCommand();

                if (formListener != null) {
                    formListener.formEventOccured(pattern);
                }
            }
        });
    }

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
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

        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(gliderRadio, gc);

        ////////////// Next row ///////////////////
        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(blinkerRadio, gc);

        ////////////// Next row ///////////////////
        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(spaceshipRadio, gc);

        ////////////// Next row ///////////////////
        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(pentaRadio, gc);

        ////////////// Next row ///////////////////
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(pulsarRadio, gc);

        ////////////// Next row ///////////////////
        gc.weightx = 1;
        gc.weighty = 2;


        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(okBtn, gc);

        ////////////// Next row ///////////////////
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.gridy+= 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(countLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(countAmountLabel, gc);
    }

    public void setData(int generationCount) {
        this.generationCount = generationCount;
        countAmountLabel.setText(String.valueOf(generationCount));
    }

    public void enableOKBtn(){
        okBtn.setEnabled(true);
    }

    public void disableOKBtn(){
        okBtn.setEnabled(false);
    }
}
