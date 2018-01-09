package com.brett.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener{
    private JButton runButton;
    private JButton pauseButton;
    private ToolbarListener toolbarListener;

    public Toolbar(){
        runButton = new JButton("Run");
        pauseButton = new JButton("Pause");

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createEtchedBorder());

        add(runButton);
        add(pauseButton);

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolbarListener.runEventOccured();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolbarListener.pauseEventOccured();
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == runButton) {
            if (toolbarListener != null) {
                toolbarListener.runEventOccured();
            }
        } else if (clicked == pauseButton) {
            if (toolbarListener != null) {
                toolbarListener.pauseEventOccured();
            }
        }
    }

    public void setToolbarListener(ToolbarListener toolbarListener) {
        this.toolbarListener = toolbarListener;
    }
}
